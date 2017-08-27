/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.enums;

public enum HtmlAnalyzerTypeEnum {

    JSOUP("htmlAnalyzerJsoup"), JERICHO("htmlAnalyzerJericho");

    private final String implementationBeanName;

    private HtmlAnalyzerTypeEnum(String implementationBeanName) {
        this.implementationBeanName = implementationBeanName;
    }

    public String getImplementationBeanName() {
        return implementationBeanName;
    }

}
