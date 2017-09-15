/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dto;

import java.util.List;
import java.util.Map;

public class PageAnalysisDto {

    private String htmlVersion;
    private String pageTitle;
    private Map<String, Long> headingsMap;

    private long internalMediaLinksCount;
    private long externalMediaLinksCount;
    private boolean containsLoginForm;

    private List<HyperMediaLinkDto> validLinks;
    private List<HyperMediaLinkDto> inValidLinks;

    public PageAnalysisDto() {
    }

    public String getHtmlVersion() {
        return htmlVersion;
    }

    public void setHtmlVersion(String htmlVersion) {
        this.htmlVersion = htmlVersion;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public Map<String, Long> getHeadingsMap() {
        return headingsMap;
    }

    public void setHeadingsMap(Map<String, Long> headingsMap) {
        this.headingsMap = headingsMap;
    }

    public long getInternalMediaLinksCount() {
        return internalMediaLinksCount;
    }

    public void setInternalMediaLinksCount(long internalMediaLinksCount) {
        this.internalMediaLinksCount = internalMediaLinksCount;
    }

    public long getExternalMediaLinksCount() {
        return externalMediaLinksCount;
    }

    public void setExternalMediaLinksCount(long externalMediaLinksCount) {
        this.externalMediaLinksCount = externalMediaLinksCount;
    }


    public boolean isContainsLoginForm() {
        return containsLoginForm;
    }

    public void setContainsLoginForm(boolean containsLoginForm) {
        this.containsLoginForm = containsLoginForm;
    }

    public List<HyperMediaLinkDto> getValidLinks() {
        return validLinks;
    }

    public void setValidLinks(List<HyperMediaLinkDto> validLinks) {
        this.validLinks = validLinks;
    }

    public List<HyperMediaLinkDto> getInValidLinks() {
        return inValidLinks;
    }

    public void setInValidLinks(List<HyperMediaLinkDto> inValidLinks) {
        this.inValidLinks = inValidLinks;
    }


}
