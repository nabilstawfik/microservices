/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.dto.HyperMediaLinkDto;
import com.microservice.dto.PageAnalysisDto;
import com.microservice.enums.LinkGroup;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public interface HtmlAnalyzer {

    /**
     * Conduct the web page analysis on the given url.
     *
     * @param url
     * @return pageAnalysisDto
     * @throws IOException
     */
    PageAnalysisDto conductWebPageAnalysis(String url) throws IOException;

    /**
     * Set the hyber media link group to internal or external according to link
     * host.
     *
     * @param hyperMediaLinkDto
     * @param url
     */
    default void setHyberMediaLinkGroup(HyperMediaLinkDto hyperMediaLinkDto, String url) {
        try {
            if (getHostName(url).equals(getHostName(hyperMediaLinkDto.getUrl()))) {
                hyperMediaLinkDto.setLinkGroup(LinkGroup.INTERNAL);
            } else {
                hyperMediaLinkDto.setLinkGroup(LinkGroup.EXTERNAL);
            }
        } catch (Exception ex) {
            hyperMediaLinkDto.setLinkGroup(LinkGroup.EXTERNAL);
        }
    }

    /**
     * Get the host name from the given url parameter.
     *
     * @param url
     * @return the domain name from the given url.
     * @throws URISyntaxException
     */
    default String getHostName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    /**
     * Validate the URL of the given hyper media link by creating a URL
     * connection with the url and check for connection response status code, if
     * the response status between (200 ,399) so it is a valid url otherwise not
     * valid URL.
     *
     * Update hyperMediaLinkDto properties (responseStatusCode,
     * ResponseDescription and validURL) according to connection status to
     * hyperMediaLinkDto.getUrl().
     *
     * @param hyperMediaLinkDto
     */
    default void validateAndUpdateHyperMediaLink(final HyperMediaLinkDto hyperMediaLinkDto) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(hyperMediaLinkDto.getUrl()).openConnection();
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(2000);

            boolean isValidCode = connection.getResponseCode() >= 200 && connection.getResponseCode() < 400;

            hyperMediaLinkDto.setResponseStatusCode(connection.getResponseCode());
            hyperMediaLinkDto.setValidURL(isValidCode);
            hyperMediaLinkDto.setResponseDescription(connection.getResponseMessage());

        } catch (Exception exception) {
            hyperMediaLinkDto.setResponseDescription(exception.getMessage());
        }
    }
}
