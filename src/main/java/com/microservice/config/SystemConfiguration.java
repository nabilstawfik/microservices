/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.config;

import com.microservice.util.PropertyUtils;
import java.util.Properties;

/**
 *
 * @author nabil
 */
public class SystemConfiguration {

    public static final String AUTHENTICATION_HDEADER_KEY_NAME;
    public static final String AUTHENTICATION_HDEADER_KEY_VALUE;

    public static final String EMAIL_SENDER_NAME;
    public static final String EMAIL_SENDER_EMAIL;

    public static final String EMAIL_MAILGUN_API_KEY;
    public static final String EMAIL_MAILGUN_URL;

    public static final String EMAIL_MANDRILL_API_KEY;
    
    public static final String EMAIL_REGEX_VALIDATOR;
    
    static {
        Properties properties = PropertyUtils.loadProperties("com.microservice.systemConfig");

        AUTHENTICATION_HDEADER_KEY_NAME = properties.getProperty("email.service.authentication.header.key.name");
        AUTHENTICATION_HDEADER_KEY_VALUE = properties.getProperty("email.service.authentication.header.key.value");

        EMAIL_SENDER_NAME = properties.getProperty("email.service.email.senderName");
        EMAIL_SENDER_EMAIL = properties.getProperty("email.service.email.senderEmail");

        EMAIL_MAILGUN_API_KEY = properties.getProperty("email.service.email.mailgun.apiKey");
        EMAIL_MAILGUN_URL = properties.getProperty("email.service.email.mailgun.url");
        
        EMAIL_MANDRILL_API_KEY = properties.getProperty("email.service.email.mandrill.apiKey");
    
        EMAIL_REGEX_VALIDATOR = properties.getProperty("email.service.email.regex.validator");
    }
}
