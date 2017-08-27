/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.dto.PageAnalysisDto;
import org.springframework.stereotype.Service;
import com.microservice.service.HtmlAnalyzer;
import java.io.IOException;

@Service("htmlAnalyzerJericho")
class HtmlAnalyzerJerichoImpl implements HtmlAnalyzer {

    /**
     * Conduct the web page analysis on the given url.
     *
     * @param url
     * @return pageAnalysisDto
     * @throws IOException
     */
    @Override
    public PageAnalysisDto conductWebPageAnalysis(String url) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
