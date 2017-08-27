/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.enums.HtmlAnalyzerTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.microservice.service.HtmlAnalyzer;
import com.microservice.service.HtmlAnalyzerFactory;

@Service("htmlParserFactory")
public class HtmlAnalyzerFactoryImpl implements HtmlAnalyzerFactory {

    @Autowired
    private ApplicationContext context;

    /**
     * Get bean with the specified parser type.
     *
     * @param hTMLParserTypeEnum
     * @return HtmlAnalyzer from the specified parser type.
     */
    @Override
    public HtmlAnalyzer getInstance(HtmlAnalyzerTypeEnum hTMLParserTypeEnum) {
        return context.getBean(hTMLParserTypeEnum.getImplementationBeanName(), HtmlAnalyzer.class);
    }
}
