package com.microservice.dto;

import com.microservice.enums.LinkGroup;

public class HyperMediaLinkDto {

    LinkGroup linkGroup;

    String tagName;

    String url;

    String responseDescription;

    int responseStatusCode;
    
    boolean validURL;

    public HyperMediaLinkDto() {
    }

    public HyperMediaLinkDto(String tagName, String url) {
        this.tagName = tagName;
        this.url = url;
    }

    public LinkGroup getLinkGroup() {
        return linkGroup;
    }

    public void setLinkGroup(LinkGroup linkGroup) {
        this.linkGroup = linkGroup;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public boolean isValidURL() {
        return validURL;
    }

    public void setValidURL(boolean validURL) {
        this.validURL = validURL;
    }

    @Override
    public String toString() {
        return "Hypermedia Link : [link group = " + linkGroup
                + ", tag name = " + tagName
                + ", url = " + url + "]";
    }

}
