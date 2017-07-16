/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dto;

/**
 *
 * @author nabil
 */
public class EmailMessageResponseDto {

    private int emailProviderId;

    private boolean success;

    private String status;

    public EmailMessageResponseDto() {}
    public EmailMessageResponseDto(int emailProviderId, boolean success, String status) {
        this.emailProviderId = emailProviderId;
        this.success = success;
        this.status = status;
    }

    public EmailMessageResponseDto(int emailProviderId, boolean success) {
        this.emailProviderId = emailProviderId;
        this.success = success;
    }

    public EmailMessageResponseDto(int emailProviderId) {
        this.emailProviderId = emailProviderId;
    }

    public int getEmailProviderId() {
        return emailProviderId;
    }

    public void setEmailProviderId(int emailProviderId) {
        this.emailProviderId = emailProviderId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
