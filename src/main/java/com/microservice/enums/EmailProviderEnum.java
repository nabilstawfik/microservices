/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.enums;

/**
 *
 * @author nabil
 */
public enum EmailProviderEnum {
    
    MAILGUN(1),
    MANDRILL(2);

    int prividerId;

    private EmailProviderEnum(int prividerId) {
        this.prividerId = prividerId;
    }

    public int getPrividerId() {
        return prividerId;
    }

}
