/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.enums.HtmlAnalyzerTypeEnum;

public interface HtmlAnalyzerFactory {

    /**
     * Get bean with the specified parser type.
     * 
     * @param hTMLParserTypeEnum
     * @return HtmlAnalyzer from the specified parser type.
     */
    HtmlAnalyzer getInstance(HtmlAnalyzerTypeEnum hTMLParserTypeEnum);
    
}
