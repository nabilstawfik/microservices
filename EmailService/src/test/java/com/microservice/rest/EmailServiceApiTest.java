package com.microservice.rest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nabil
 */
import com.microservice.config.SystemConfiguration;
import com.microservice.dto.EmailMessageDto;
import com.microservice.dto.EmailMessageResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmailServiceApiTest {

    private static final String EMAIL_SERVICE_END_POINT_URL = "/microservices/emailer/send";

    @Autowired
    private TestRestTemplate restTemplate;
    
    HttpHeaders headers;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(SystemConfiguration.AUTHENTICATION_HDEADER_KEY_NAME, SystemConfiguration.AUTHENTICATION_HDEADER_KEY_VALUE);
    }

    @After
    public void tearDown() {
        headers = null;
    }

    @Test
    public void validAuthentication() {

        EmailMessageDto emailMessageDto = new EmailMessageDto("nabilstawfik@gmail.com", "Valid Authentication Test", "Email Body");
        HttpEntity httpEntity = new HttpEntity(emailMessageDto, headers);

        ResponseEntity<EmailMessageResponseDto> entity = this.restTemplate.postForEntity(EMAIL_SERVICE_END_POINT_URL, httpEntity, EmailMessageResponseDto.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void inValidAuthentication() {

        EmailMessageDto emailMessageDto = new EmailMessageDto("nabilstawfik@gmail.com", "Invalid Authentication Test", "Email Body");
        headers.add(SystemConfiguration.AUTHENTICATION_HDEADER_KEY_NAME, "dummy key");
        HttpEntity httpEntity = new HttpEntity(emailMessageDto, headers);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(EMAIL_SERVICE_END_POINT_URL, httpEntity, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void sendEmailWithoutRequestDto() {
        HttpEntity httpEntity = new HttpEntity(null, headers);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(EMAIL_SERVICE_END_POINT_URL, httpEntity, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void invalidEmailAddress() {
        EmailMessageDto emailMessageDto = new EmailMessageDto("nabilstawfik.me", "Invalid Email Address", "Email Body");
        HttpEntity httpEntity = new HttpEntity(emailMessageDto, headers);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(EMAIL_SERVICE_END_POINT_URL, httpEntity, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void emptyEmailAddress() {
        EmailMessageDto emailMessageDto = new EmailMessageDto(null, "Empty Email Address", "Email Body");
        HttpEntity httpEntity = new HttpEntity(emailMessageDto, headers);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(EMAIL_SERVICE_END_POINT_URL, httpEntity, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void emptyEmailSubject() {
        EmailMessageDto emailMessageDto = new EmailMessageDto("nabilstawfik@gmail.com", null, "Email Body");
        HttpEntity httpEntity = new HttpEntity(emailMessageDto, headers);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(EMAIL_SERVICE_END_POINT_URL, httpEntity, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void emptyEmailBody() {
        EmailMessageDto emailMessageDto = new EmailMessageDto("nabilstawfik@gmail.com", "Email Subject", null);
        HttpEntity httpEntity = new HttpEntity(emailMessageDto, headers);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(EMAIL_SERVICE_END_POINT_URL, httpEntity, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void emptyEmailDto() {
        EmailMessageDto emailMessageDto = new EmailMessageDto(null, null, null);
        HttpEntity httpEntity = new HttpEntity(emailMessageDto, headers);

        ResponseEntity<String> entity = this.restTemplate.postForEntity(EMAIL_SERVICE_END_POINT_URL, httpEntity, String.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
