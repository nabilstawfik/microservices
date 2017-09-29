/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice;

import com.microservice.config.SystemConfiguration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author nabil
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractRestBaseTest {

    @Autowired
    protected TestRestTemplate restTemplate;
    
    protected HttpHeaders headers;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        String plainClientCredentials = "nabil:123456";
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
        headers.add(SystemConfiguration.AUTHENTICATION_HDEADER_KEY_NAME, "Basic " + base64ClientCredentials);
    }
    
}
