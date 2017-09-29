/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.dto.AuthorDto;
import com.microservice.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.repository.AuthorRepository;
import com.microservice.service.AuthorService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.Sort;

/**
 *
 * @author nabil
 */
@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        Author author;
        if (authorDto.getAuthorId() != 0) {
            author = authorRepository.findOne(authorDto.getAuthorId());
        } else {
            author = mapAuthorDtoToAuthor(authorDto);
        }
        author = authorRepository.save(author);
        authorDto.setAuthorId(author.getId());
        return authorDto;
    }

    @Override
    public List<AuthorDto> findAllOrderByNickNameAsc() {
        Stream<Author> authorStream = StreamSupport.stream(authorRepository.findAll(new Sort(Sort.Direction.ASC, "nickName")).spliterator(), false);
        return authorStream.map(author -> mapAuthorToAuthorDto(author)).collect(Collectors.toList());
    }

    private AuthorDto mapAuthorToAuthorDto(Author author) {
        return new AuthorDto(author.getId(), author.getNickName());
    }

    private Author mapAuthorDtoToAuthor(AuthorDto authorDto) {
        return new Author(authorDto.getNickName());
    }
}
