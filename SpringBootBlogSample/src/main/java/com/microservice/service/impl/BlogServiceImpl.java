/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.dto.AuthorDto;
import com.microservice.dto.BlogDto;
import com.microservice.exception.InvalidAuthorException;
import com.microservice.model.Author;
import com.microservice.model.Blog;
import com.microservice.repository.AuthorRepository;
import com.microservice.repository.BlogRepository;
import com.microservice.service.BlogService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author nabil
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public BlogDto save(BlogDto blogDto) throws InvalidAuthorException {
        Blog blog;
        if (blogDto.getBlogId() == 0) {
            blog = mapBlogDtoToBlog(blogDto);
        } else {
            blog = blogRepository.findOne(blogDto.getBlogId());
            blog.setTitle(blogDto.getTitle());
            blog.setBody(blogDto.getBody());
        }
        return mapBlogToBlogDto(blogRepository.save(blog));
    }

    @Override
    public List<BlogDto> findAllByOrderByCreationTimeDesc(int page, int size) {
        List<Blog> blogList = blogRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "creationTime")).getContent();
        return blogList.stream().map(blog -> mapBlogToBlogDto(blog)).collect(Collectors.toList());
    }

    private BlogDto mapBlogToBlogDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setBlogId(blog.getId());
        blogDto.setBody(blog.getBody());
        blogDto.setTitle(blog.getTitle());
        blogDto.setCreationTime(blog.getCreationTime());
        blogDto.setAuthorDto(new AuthorDto(blog.getAuthor().getId(), blog.getAuthor().getNickName()));
        return blogDto;
    }

    private Blog mapBlogDtoToBlog(BlogDto blogDto) throws InvalidAuthorException {
        Author author = null;
        if (blogDto.getAuthorDto() != null && blogDto.getAuthorDto().getAuthorId() != 0) {
            author = authorRepository.findOne(blogDto.getAuthorDto().getAuthorId());
        }
        if (author == null) {
            throw new InvalidAuthorException();
        }
        return new Blog(blogDto.getTitle(), blogDto.getBody(), blogDto.getCreationTime(), author);
    }
}
