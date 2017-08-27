/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.dto.PageAnalysisDto;
import com.microservice.dto.HyperMediaLinkDto;
import com.microservice.enums.LinkGroup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.microservice.service.HtmlAnalyzer;
import java.util.Optional;

@Service(value = "htmlAnalyzerJsoup")
@Primary
class HtmlAnalyzerJsoupImpl implements HtmlAnalyzer {

    /**
     * Conduct the web page analysis on the given url.
     *
     * @param url
     * @return pageAnalysisDto
     * @throws IOException
     */
    @Override
    public PageAnalysisDto conductWebPageAnalysis(String url) throws IOException {
        PageAnalysisDto pageAnalysisDto = new PageAnalysisDto();
        Document document = Jsoup.connect(url).get();

        pageAnalysisDto.setPageTitle(document.title());
        pageAnalysisDto.setHtmlVersion(getHtmlVersion(document));
        pageAnalysisDto.setHeadings(getHeadingsGroupedByHeadinglevel(document));

        List<HyperMediaLinkDto> hyperMediaLinkDtos = getHyperMediaLinksList(document);

        pageAnalysisDto.setHyperMediaLinksGroupedByLinkGroup(getHyperMediaLinksGroupedByLinkGroup(hyperMediaLinkDtos));
        pageAnalysisDto.setHyperMediaLinksGroupedByLinkResponseStatusCode(getHyperMediaLinksGroupedByIsValidURL(hyperMediaLinkDtos));

        pageAnalysisDto.setContainLoginForm(isContainsLoginForm(document));

        return pageAnalysisDto;
    }

    /**
     * Get the Html version from the given document.
     *
     * @param document
     * @return string html version.
     */
    public String getHtmlVersion(Document document) {
        List<Node> nodes = document.childNodes();
        return nodes.stream().filter(node -> node instanceof DocumentType).map((node) -> {
            DocumentType documentType = (DocumentType) node;
            String htmlVersion = documentType.attr("publicid");
            return StringUtils.isEmpty(htmlVersion) ? "HTML 5" : htmlVersion;
        }).collect(Collectors.joining());
    }

    /**
     * Get html heading grouped by heading level and count of each heading.
     *
     * @param document
     * @return map Key is the heading name and Value is the heading count.
     */
    public Map<String, Long> getHeadingsGroupedByHeadinglevel(Document document) {
        Elements hTags = document.select("h1, h2, h3, h4, h5, h6");
        return hTags.parallelStream().collect(Collectors.groupingBy(element -> element.tagName(), Collectors.counting()));
    }

    /**
     * Get all hyper media links on the page grouped to internal or external and
     * count of each group.
     *
     * @param hyperMediaLinkDtos
     * @return map Key is the group name and Value is the count of links on the
     * group.
     */
    public Map<LinkGroup, Long> getHyperMediaLinksGroupedByLinkGroup(List<HyperMediaLinkDto> hyperMediaLinkDtos) {
        return hyperMediaLinkDtos.parallelStream().collect(Collectors.groupingBy(HyperMediaLinkDto::getLinkGroup, Collectors.counting()));
    }

    /**
     * Get all hyper media links on the page grouped to true or false, true if
     * valid url and else false.
     *
     * @param hyperMediaLinkDtos
     * @return map Key is true/false and Value list of HyperMediaLinkDto.
     */
    public Map<Boolean, List<HyperMediaLinkDto>> getHyperMediaLinksGroupedByIsValidURL(List<HyperMediaLinkDto> hyperMediaLinkDtos) {
        return hyperMediaLinkDtos.parallelStream().collect(Collectors.groupingBy(HyperMediaLinkDto::isValidURL, Collectors.toList()));
    }

    /**
     * Get all hyper media links on the given document.
     *
     * @param document
     * @return <code>List<HyperMediaLinkDto></code> all hyper media links on the
     * given document.
     */
    public List<HyperMediaLinkDto> getHyperMediaLinksList(Document document) {
        String url = document.baseUri();
        List<HyperMediaLinkDto> hyperMediaLinks = new ArrayList<>();

        Elements media = document.select("a[href], link[href], [src]");

        media.parallelStream().forEach((Element link) -> {
            String absUrl = ("a".equals(link.tagName()) || "link".equals(link.tagName())) ? link.absUrl("href") : link.absUrl("src");
            HyperMediaLinkDto hyperMediaLinkDto = new HyperMediaLinkDto(link.tagName(), absUrl);
            setHyberMediaLinkGroup(hyperMediaLinkDto, url);
            setHyperMediaLinkValidationProperties(new ArrayList(hyperMediaLinks), hyperMediaLinkDto);
            hyperMediaLinks.add(hyperMediaLinkDto);
        });
        return hyperMediaLinks;
    }

    /**
     * Set the given hyperMediaLinkDto param with the validation
     * properties(responseStatusCode, responseDescription and validURL).
     *
     * @param hyperMediaLinks
     * @param hyperMediaLinkDto
     */
    public void setHyperMediaLinkValidationProperties(final List<HyperMediaLinkDto> hyperMediaLinks, final HyperMediaLinkDto hyperMediaLinkDto) {
        Optional<HyperMediaLinkDto> loadedLink = hyperMediaLinks.parallelStream().filter(mediaLink -> mediaLink.getUrl().equals(hyperMediaLinkDto.getUrl())).findFirst();
        if (loadedLink.isPresent()) {
            hyperMediaLinkDto.setResponseStatusCode(loadedLink.get().getResponseStatusCode());
            hyperMediaLinkDto.setResponseDescription(loadedLink.get().getResponseDescription());
            hyperMediaLinkDto.setValidURL(loadedLink.get().isValidURL());
        } else {
            validateAndUpdateHyperMediaLink(hyperMediaLinkDto);
        }
    }

    /**
     * Check if the given document includes login form.
     *
     * @param document
     * @return boolean True if the document includes login form else False.
     */
    public boolean isContainsLoginForm(Document document) {
        Elements loginElements = document.select("form[action*=/login], input[id*=login], input[name*=login]");
        return loginElements.size() > 0;
    }
}
