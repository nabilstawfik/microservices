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
import com.microservice.AbstractRestBaseTest;
import com.microservice.dto.AuthorDto;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpEntity;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.HttpMethod;

public class AuthorRestControllerTest extends AbstractRestBaseTest {

    private static final String INSTANCE_SERVICE_END_POINT_URL = "/api/author-service";

    @Test
    public void saveTest() {
        HttpEntity httpEntity = new HttpEntity(new AuthorDto("Nabil Tawfik"), headers);
        ResponseEntity<AuthorDto> actualResponse = this.restTemplate.postForEntity(INSTANCE_SERVICE_END_POINT_URL + "/save", httpEntity, AuthorDto.class);

        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody().getAuthorId()).isNotEqualTo(0);
    }

    @Test
    public void findAllOrderByNickNameAscTest() {
        HttpEntity httpEntity = new HttpEntity(null, headers);
        ResponseEntity<AuthorDto[]> actualResponse = restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL + "/get-authors", HttpMethod.GET, httpEntity, AuthorDto[].class);
        List<AuthorDto> actualList = Arrays.asList(actualResponse.getBody());
        assertThat(actualList).isNotNull();
    }
}
