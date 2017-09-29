/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author nabil
 */
public class PropertyUtils {

    public static String loadProperty(final String propertyFileName, final String propertyName) {
        return loadProperties(propertyFileName).getProperty(propertyName);
    }

    public static Properties loadProperties(String name) {

        Properties result = null;

        InputStream in = null;
        try {
            name = name.replace('/', '.');
            // Throws MissingResourceException on lookup failures:
            final ResourceBundle rb = ResourceBundle.getBundle(name,
                    Locale.getDefault(), Thread.currentThread().getContextClassLoader());

            result = new Properties();
            for (Enumeration keys = rb.getKeys(); keys.hasMoreElements();) {
                final String key = (String) keys.nextElement();
                final String value = rb.getString(key);

                result.put(key, value);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("could not load [" + name + "]");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Throwable ignore) {
                }
            }
        }
        return result;
    }

}
