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
import com.microservice.dto.BlogDto;
import com.microservice.exception.InvalidAuthorException;
import com.microservice.service.AuthorService;
import com.microservice.service.BlogService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BLogRestControllerTest extends AbstractRestBaseTest {

    private static final String INSTANCE_SERVICE_END_POINT_URL = "/api/blog-service";

    AuthorDto authorDto;

    @Autowired
    BlogService blogService;
    @Autowired
    AuthorService authorService;

    @Before
    public void setup() {
        authorDto = authorService.save(new AuthorDto("Author Name"));
    }

    @Test
    public void saveTest() {
        BlogDto blogDto = new BlogDto("Title", "Body", Date.valueOf(LocalDate.now()), authorDto);
        HttpEntity httpEntity = new HttpEntity(blogDto, headers);
        ResponseEntity<BlogDto> actual = this.restTemplate.postForEntity(INSTANCE_SERVICE_END_POINT_URL + "/save", httpEntity, BlogDto.class);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody().getBlogId()).isNotEqualTo(0);
    }

    @Test
    public void updateTitleTest() throws InvalidAuthorException {
        BlogDto blogDto = new BlogDto("Title", "Body", Date.valueOf(LocalDate.now()), authorDto);
        blogDto = blogService.save(blogDto);

        blogDto.setTitle("Updated Title");
        HttpEntity httpEntity = new HttpEntity(blogDto, headers);
        ResponseEntity<BlogDto> actualResponse = this.restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL + "/update", HttpMethod.PUT, httpEntity, BlogDto.class);

        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody().getBlogId()).isEqualTo(blogDto.getBlogId());
        assertThat(actualResponse.getBody().getTitle()).isEqualTo("Updated Title");
    }

    @Test
    public void updateBodyTest() throws InvalidAuthorException {
        BlogDto blogDto = new BlogDto("Title", "Body", Date.valueOf(LocalDate.now()), authorDto);
        blogDto = blogService.save(blogDto);

        blogDto.setBody("Updated Body");
        HttpEntity httpEntity = new HttpEntity(blogDto, headers);
        ResponseEntity<BlogDto> actualResponse = this.restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL + "/update", HttpMethod.PUT, httpEntity, BlogDto.class);

        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody().getBlogId()).isEqualTo(blogDto.getBlogId());
        assertThat(actualResponse.getBody().getBody()).isEqualTo("Updated Body");
    }
    
    @Test
    public void updateTitleAndBodyTest() throws InvalidAuthorException {
        BlogDto blogDto = new BlogDto("Title", "Body", Date.valueOf(LocalDate.now()), authorDto);
        blogDto = blogService.save(blogDto);
        
        blogDto.setTitle("Updated Title");
        blogDto.setBody("Updated Body");
        
        HttpEntity httpEntity = new HttpEntity(blogDto, headers);
        ResponseEntity<BlogDto> actualResponse = this.restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL + "/update", HttpMethod.PUT, httpEntity, BlogDto.class);

        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody().getBlogId()).isEqualTo(blogDto.getBlogId());
        assertThat(actualResponse.getBody().getTitle()).isEqualTo("Updated Title");
        assertThat(actualResponse.getBody().getBody()).isEqualTo("Updated Body");
    }

    @Test
    public void findAllByOrderByCreationTimeDescTest() {
        HttpEntity httpEntity = new HttpEntity(null, headers);
        ResponseEntity<BlogDto[]> actualResponse = restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL + "/get-blogs/0/20", HttpMethod.GET, httpEntity, BlogDto[].class);
        List<BlogDto> actualList = Arrays.asList(actualResponse.getBody());

        assertThat(actualList).isNotNull();
    }

}
