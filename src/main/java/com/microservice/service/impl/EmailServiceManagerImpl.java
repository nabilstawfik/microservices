/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.config.SystemConfiguration;
import com.microservice.dto.EmailMessageDto;
import com.microservice.dto.EmailMessageResponseDto;
import com.microservice.exceptions.InvalidEmailAddressException;
import com.microservice.service.EmailServiceManager;
import com.microservice.service.EmailerService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author nabil
 */
@Service("emailServiceManager")
public class EmailServiceManagerImpl implements EmailServiceManager{

    private final EmailerService[] emailerServices = {new MailgunEmailerServiceImpl(), new MandrillEmailerServiceImpl()};

    @Override
    public EmailMessageResponseDto sendEmail(EmailMessageDto emailMessageDto) throws InvalidEmailAddressException {
        if (!isValidEmail(emailMessageDto.getTo())) {
            throw new InvalidEmailAddressException("Invalid Email Address: " + emailMessageDto.getTo());
        }
        EmailMessageResponseDto emailMessageResponseDto = null;
        for (EmailerService emailerService : emailerServices) {
            emailMessageResponseDto = emailerService.send(emailMessageDto);
            if (emailMessageResponseDto.isSuccess()) {
                return emailMessageResponseDto;
            }
        }
        return emailMessageResponseDto;
    }

    public boolean isValidEmail(String email) {
        if (!StringUtils.isEmpty(email)) {
            Pattern pattern = Pattern.compile(SystemConfiguration.EMAIL_REGEX_VALIDATOR);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }
}
