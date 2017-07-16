/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.dto.EmailMessageDto;
import com.microservice.dto.EmailMessageResponseDto;
import com.microservice.exceptions.InvalidEmailAddressException;

/**
 *
 * @author nabil
 */
public interface EmailServiceManager {

    EmailMessageResponseDto sendEmail(EmailMessageDto emailMessageDto) throws InvalidEmailAddressException;
    
}
