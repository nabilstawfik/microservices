/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.util;

import com.microservice.config.SystemConfiguration;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 *
 * @author nabil
 */
public class IdGeneratorUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
    
    public static String generateCorrelationId(){
        
        StringBuilder correlationId = new StringBuilder();
        Random rnd = new Random();
        while (correlationId.length() < 20) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SystemConfiguration.CORRELATION_ID_CHARS.length());
            correlationId.append(SystemConfiguration.CORRELATION_ID_CHARS.charAt(index));
        }
        String saltStr = correlationId.toString();
        return saltStr;
    }
    
    public static boolean isValidUUID(String uuidString){
        if (!StringUtils.isEmpty(uuidString)) {
            Pattern pattern = Pattern.compile(SystemConfiguration.UUID_REGEX_VALIDATOR);
            Matcher matcher = pattern.matcher(uuidString);
            return matcher.matches();
        }
        return false;
    }

}
