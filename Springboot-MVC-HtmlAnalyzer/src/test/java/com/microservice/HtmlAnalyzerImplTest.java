/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice;

import com.microservice.dto.PageAnalysisDto;
import com.microservice.service.HtmlAnalyzer;
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
import org.junit.After;
import static org.assertj.core.api.Assertions.assertThat;

@PrepareForTest({Jsoup.class})
public class HtmlAnalyzerImplTest extends AbstractMockitoBaseTest {

    @Autowired
    private HtmlAnalyzer htmlAnalyzer;

    
    private PageAnalysisDto pageAnalysisDto;
    private final String baseUrl = "http://localhost:8086/";

    @Before
    public void setup() throws IOException {
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
    public void testAnalyzer() {
        assertThat(pageAnalysisDto.getInternalMediaLinksCount()).isEqualTo(0);
        assertThat(pageAnalysisDto.getExternalMediaLinksCount()).isEqualTo(3);
        assertThat(pageAnalysisDto.getValidLinks().size()).isEqualTo(3);
        assertThat(pageAnalysisDto.getInValidLinks()).isNull();
        assertThat(pageAnalysisDto.getPageTitle()).isEqualTo("Welcome !");
        assertThat(pageAnalysisDto.getHtmlVersion()).isEqualTo("HTML 5");
        assertThat(pageAnalysisDto.isContainsLoginForm()).isEqualTo(false);
    }

}
