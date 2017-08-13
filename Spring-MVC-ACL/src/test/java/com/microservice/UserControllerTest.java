/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice;

import com.microservice.dto.UserDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author nabil
 */
public class UserControllerTest extends AbstractSecurityTest {

    private static final String END_POINT_URL = "http://localhost:8080/rest/user-service/";

    private RestTemplate restTemplate;

    HttpHeaders headers;

    UserDto userDto;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        userDto = new UserDto();
    }

    @After
    public void tearDown() {
        headers = null;
        userDto = null;
    }

    @Test
    public void addUser() {
        userDto.setEmail(Math.random() + "nabil2@gmail.com");
        userDto.setPassword("123456");
        HttpEntity httpEntity = new HttpEntity(userDto, headers);
        ResponseEntity<UserDto> entity = this.restTemplate.postForEntity(END_POINT_URL + "add-user", httpEntity, UserDto.class);
        Assert.assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void showUser() {
        addUser();
        UserDto userDto1 = restTemplate.getForObject(END_POINT_URL + "show-user/" + 1, UserDto.class);
        Assert.assertNotEquals(userDto1, null);
    }

    @Test
    public void listUser() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity(END_POINT_URL + "list-all", String.class);
        Assert.assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void updateUser() {
        userDto.setEmail("nabil_test@gmail.com");
        userDto.setPassword("123456");
        userDto.setOldPassword("123456");
        HttpEntity httpEntity = new HttpEntity(userDto, headers);
        ResponseEntity<UserDto> entity = restTemplate.exchange(END_POINT_URL + "/update-user/1", HttpMethod.PUT, httpEntity, UserDto.class);
        Assert.assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteUser() {
        ResponseEntity<String> entity = restTemplate.exchange(END_POINT_URL + "/delete-user/1", HttpMethod.DELETE, null, String.class);
        Assert.assertEquals(entity.getStatusCode(), HttpStatus.OK);

    }
}
