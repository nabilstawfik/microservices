/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.integration;

import com.microservice.repository.AuthorRepository;
import com.microservice.AbstractBaseTest;
import com.microservice.dto.AuthorDto;
import com.microservice.service.AuthorService;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author nabil
 */
public class AuthorServiceImplIntegrationTest extends AbstractBaseTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorService authorService;

    @Test
    public void saveTest() {
        AuthorDto actual = authorService.save(new AuthorDto("Nabil"));
        assertThat(actual).isNotNull();
        assertThat(actual.getAuthorId()).isNotEqualTo(0);
    }

    @Test
    public void updateAuthorNickNameTest() {
        AuthorDto actual = authorService.save(new AuthorDto("Nabil Tawfik"));
        assertThat(actual).isNotNull();
        assertThat(actual.getAuthorId()).isNotEqualTo(0);

        actual.setNickName("Nabil Saad Tawfik");
        AuthorDto actualUpdated = authorService.save(actual);

        assertThat(actualUpdated).isNotNull();
        assertThat(actualUpdated.getNickName()).isEqualTo("Nabil Saad Tawfik");
        assertThat(actualUpdated.getAuthorId()).isEqualTo(actual.getAuthorId());
    }

    @Test
    public void findAllByOrderByNickNameAscTest() {
        authorRepository.deleteAll();
        
        authorService.save(new AuthorDto("Tom"));
        authorService.save(new AuthorDto("Andre"));
        authorService.save(new AuthorDto("Nabil"));
        List<AuthorDto> actuals = authorService.findAllOrderByNickNameAsc();
        
        assertThat(actuals).isNotNull();
        assertThat(actuals.get(0).getNickName()).isEqualTo("Andre");
        assertThat(actuals.get(1).getNickName()).isEqualTo("Nabil");
        assertThat(actuals.get(2).getNickName()).isEqualTo("Tom");
    }

}
