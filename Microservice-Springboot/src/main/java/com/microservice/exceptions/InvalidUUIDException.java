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
public class InvalidUUIDException extends Exception {

    private String message;

    public InvalidUUIDException() {
        this.message = "Invalid UUID.";
    }

    public InvalidUUIDException(String msg) {
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
