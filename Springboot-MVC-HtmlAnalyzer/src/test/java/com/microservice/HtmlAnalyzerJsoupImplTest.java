/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice;

import com.microservice.dto.PageAnalysisDto;
import com.microservice.enums.HtmlAnalyzerTypeEnum;
import com.microservice.enums.LinkGroup;
import com.microservice.service.HtmlAnalyzer;
import com.microservice.service.HtmlAnalyzerFactory;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Before;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;

@PrepareForTest({Jsoup.class})
public class HtmlAnalyzerJsoupImplTest extends AbstractMockitoBaseTest {

    @Autowired
    private HtmlAnalyzerFactory htmlAnalyzerFactory;

    private HtmlAnalyzer htmlAnalyzer;
    private PageAnalysisDto pageAnalysisDto;
    private final String baseUrl = "http://localhost:8086/";

    @Before
    public void setup() throws IOException {
        htmlAnalyzer = htmlAnalyzerFactory.getInstance(HtmlAnalyzerTypeEnum.JSOUP);
        mockJsoupConnection();
        pageAnalysisDto = htmlAnalyzer.conductWebPageAnalysis(baseUrl);
    }

    @After
    public void tearDown() {
        pageAnalysisDto = null;
    }

    public void mockJsoupConnection() throws IOException {
        Connection connection = Mockito.mock(Connection.class);
        Document document = Jsoup.parse(new File(this.getClass().getResource("/html-to-parse.txt").getFile()), "UTF-8");
        document.setBaseUri(baseUrl);

        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
        Mockito.when(connection.get()).thenReturn(document);
    }

    @Test
    public void testInternalCount() {

        assertThat(pageAnalysisDto.getHyperMediaLinksGroupedByLinkGroup().get(LinkGroup.INTERNAL)).isNull();
    }

    @Test
    public void testExternalCount() {

        assertThat(pageAnalysisDto.getHyperMediaLinksGroupedByLinkGroup().get(LinkGroup.EXTERNAL)).isEqualTo(3);
    }

    @Test
    public void testValidCount() {

        assertThat(pageAnalysisDto.getHyperMediaLinksGroupedByLinkResponseStatusCode().get(true).size()).isEqualTo(3);
    }

    @Test
    public void testInvalidCount() {

        assertThat(pageAnalysisDto.getHyperMediaLinksGroupedByLinkResponseStatusCode().get(false)).isNull();
    }

    @Test
    public void testDocumentTitle() {

        assertThat(pageAnalysisDto.getPageTitle()).isEqualTo("Welcome !");
    }

    @Test
    public void testHtmlVersion() {

        assertThat(pageAnalysisDto.getHtmlVersion()).isEqualTo("HTML 5");
    }

    @Test
    public void testIsContainsLogin() {
        assertThat(pageAnalysisDto.isContainLoginForm()).isEqualTo(false);
    }

}
