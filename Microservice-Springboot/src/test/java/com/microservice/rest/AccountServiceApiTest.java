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
import com.microservice.model.Account;
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
import org.junit.After;
import org.junit.Before;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testing")
public class AccountServiceApiTest {

    private static final String INSTANCE_SERVICE_END_POINT_URL = "/microservice/account-service/save-account";

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(SystemConfiguration.AUTHENTICATION_HDEADER_KEY_NAME, SystemConfiguration.AUTHENTICATION_HDEADER_KEY_VALUE);
    }

    @After
    public void tearDown() {
        headers = null;
    }

    @Test
    public void saveAccount() {
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("name", "Nabil Tawfik");
        bodyMap.add("email", "nabil@arkdev.net");
        HttpEntity httpEntity = new HttpEntity(bodyMap, headers);
        ResponseEntity<Account> entity = this.restTemplate.postForEntity(INSTANCE_SERVICE_END_POINT_URL, httpEntity, Account.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void inValidAuthentication() {
        headers.remove(SystemConfiguration.AUTHENTICATION_HDEADER_KEY_NAME);
        HttpEntity httpEntity = new HttpEntity(null, headers);
        ResponseEntity<String> entity = this.restTemplate.postForEntity(INSTANCE_SERVICE_END_POINT_URL, httpEntity, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

}
