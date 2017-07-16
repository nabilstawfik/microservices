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

    
    public static final String UUID_REGEX_VALIDATOR;
    public static final String CORRELATION_ID_CHARS;
    
    static {
        Properties properties = PropertyUtils.loadProperties("com.microservice.systemConfig");

        AUTHENTICATION_HDEADER_KEY_NAME = properties.getProperty("microservice.authentication.header.key.name");
        AUTHENTICATION_HDEADER_KEY_VALUE = properties.getProperty("microservice.authentication.header.key.value");

        UUID_REGEX_VALIDATOR = properties.getProperty("microservice.uuid.regex.validator");
        CORRELATION_ID_CHARS = properties.getProperty("microservice.correlation.id.chars");
    }
}
