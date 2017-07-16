/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.dto.EmailMessageDto;
import com.microservice.dto.EmailMessageResponseDto;

/**
 *
 * @author nabil
 */
public interface EmailerService {

    EmailMessageResponseDto send(EmailMessageDto emailMessageDto);
}
