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
    public static final String AUTHENTICATION_REALM_NAME;

    static {
        Properties properties = PropertyUtils.loadProperties("application");

        AUTHENTICATION_HDEADER_KEY_NAME = properties.getProperty("microservice.authentication.header.key.name");
        AUTHENTICATION_REALM_NAME = properties.getProperty("microservice.authentication.real.name");

    }
}
