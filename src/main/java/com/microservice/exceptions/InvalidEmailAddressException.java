/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.exceptions;

/**
 *
 * @author nabil
 */
public class InvalidEmailAddressException extends Exception {

    private String message;

    public InvalidEmailAddressException() {
        this.message = "Invalid Email Address.";
    }

    public InvalidEmailAddressException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
