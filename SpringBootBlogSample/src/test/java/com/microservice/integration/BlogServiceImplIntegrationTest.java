/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.integration;

import com.microservice.dto.AuthorDto;
import com.microservice.dto.BlogDto;
import com.microservice.exception.InvalidAuthorException;
import com.microservice.repository.BlogRepository;
import com.microservice.AbstractBaseTest;
import com.microservice.service.AuthorService;
import com.microservice.service.BlogService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author nabil
 */
public class BlogServiceImplIntegrationTest extends AbstractBaseTest {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    BlogService blogService;
    @Autowired
    AuthorService authorService;

    @Test
    public void saveTest() throws InvalidAuthorException {
        AuthorDto authorDto = authorService.save(new AuthorDto("Nabil"));
        BlogDto blogDto = new BlogDto("Title1", "Body1", Date.valueOf(LocalDate.now()), authorDto);
        blogDto = blogService.save(blogDto);

        assertThat(blogDto).isNotNull();
        assertThat(blogDto.getBlogId()).isNotEqualTo(0);
    }

    @Test
    public void updateBlogTitleTest() throws InvalidAuthorException {
        AuthorDto authorDto = authorService.save(new AuthorDto("Nabil"));
        BlogDto actual = blogService.save(new BlogDto("Title1", "Body1", Date.valueOf(LocalDate.now()), authorDto));

        assertThat(actual).isNotNull();
        assertThat(actual.getBlogId()).isNotEqualTo(0);

        actual.setTitle("Updated Title");
        BlogDto actualUpdated = blogService.save(actual);
        
        assertThat(actualUpdated).isNotNull();
        assertThat(actualUpdated.getTitle()).isEqualTo("Updated Title");
        assertThat(actualUpdated.getBlogId()).isEqualTo(actual.getBlogId());

    }
    
    @Test
    public void updateBlogBodyTest() throws InvalidAuthorException {
        AuthorDto authorDto = authorService.save(new AuthorDto("Nabil"));
        BlogDto actual = blogService.save(new BlogDto("Title1", "Body1", Date.valueOf(LocalDate.now()), authorDto));

        assertThat(actual).isNotNull();
        assertThat(actual.getBlogId()).isNotEqualTo(0);

        actual.setBody("Updated Body");
        BlogDto actualUpdated = blogService.save(actual);
        
        assertThat(actualUpdated).isNotNull();
        assertThat(actualUpdated.getBody()).isEqualTo("Updated Body");
        assertThat(actualUpdated.getBlogId()).isEqualTo(actual.getBlogId());

    }

    @Test
    public void updateBlogTitleAndBodyTest() throws InvalidAuthorException {
        AuthorDto authorDto = authorService.save(new AuthorDto("Nabil"));
        BlogDto actual = blogService.save(new BlogDto("Title1", "Body1", Date.valueOf(LocalDate.now()), authorDto));

        assertThat(actual).isNotNull();
        assertThat(actual.getBlogId()).isNotEqualTo(0);

        actual.setTitle("Updated Title");
        actual.setBody("Updated Body");
        BlogDto actualUpdated = blogService.save(actual);
        
        assertThat(actualUpdated).isNotNull();
        assertThat(actualUpdated.getTitle()).isEqualTo("Updated Title");
        assertThat(actualUpdated.getBody()).isEqualTo("Updated Body");
        assertThat(actualUpdated.getBlogId()).isEqualTo(actual.getBlogId());

    }
    
    @Test
    public void findAllByOrderByCreationTimeDescTest() throws InvalidAuthorException {
        blogRepository.deleteAll();
        AuthorDto authorDto = authorService.save(new AuthorDto("Nabil"));
        BlogDto blogDto1 = new BlogDto("Title1", "Body1", Date.valueOf(LocalDate.now()), authorDto);
        BlogDto blogDto2 = new BlogDto("Title2", "Body2", Date.valueOf(LocalDate.now().plusDays(1)), authorDto);
        blogService.save(blogDto1);
        blogService.save(blogDto2);

        List<BlogDto> actualBlogDtoList = blogService.findAllByOrderByCreationTimeDesc(0, 20);
        assertThat(actualBlogDtoList).isNotNull();
        assertThat(actualBlogDtoList.get(0).getTitle()).isEqualTo("Title2");
        assertThat(actualBlogDtoList.get(1).getTitle()).isEqualTo("Title1");
    }
}
