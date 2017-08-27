/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dto;

import com.microservice.enums.LinkGroup;
import java.util.List;
import java.util.Map;

public class PageAnalysisDto {

    private String htmlVersion;
    private String pageTitle;
    private Map<String, Long> headings;
    private Map<LinkGroup, Long> hyperMediaLinksGroupedByLinkGroup;
    private boolean containLoginForm;
    private Map<Boolean, List<HyperMediaLinkDto>> hyperMediaLinksGroupedByLinkResponseStatusCode;

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

    public Map<String, Long> getHeadings() {
        return headings;
    }

    public void setHeadings(Map<String, Long> headings) {
        this.headings = headings;
    }

    public Map<LinkGroup, Long> getHyperMediaLinksGroupedByLinkGroup() {
        return hyperMediaLinksGroupedByLinkGroup;
    }

    public void setHyperMediaLinksGroupedByLinkGroup(Map<LinkGroup, Long> hyperMediaLinksGroupedByLinkGroup) {
        this.hyperMediaLinksGroupedByLinkGroup = hyperMediaLinksGroupedByLinkGroup;
    }

    public boolean isContainLoginForm() {
        return containLoginForm;
    }

    public void setContainLoginForm(boolean containLoginForm) {
        this.containLoginForm = containLoginForm;
    }

    public Map<Boolean, List<HyperMediaLinkDto>> getHyperMediaLinksGroupedByLinkResponseStatusCode() {
        return hyperMediaLinksGroupedByLinkResponseStatusCode;
    }

    public void setHyperMediaLinksGroupedByLinkResponseStatusCode(Map<Boolean, List<HyperMediaLinkDto>> hyperMediaLinksGroupedByLinkResponseStatusCode) {
        this.hyperMediaLinksGroupedByLinkResponseStatusCode = hyperMediaLinksGroupedByLinkResponseStatusCode;
    }

}
