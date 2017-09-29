/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author nabil
 */
public class BlogDto extends ResourceSupport {

    @Getter
    @Setter
    private long blogId;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String body;
    @Getter
    @Setter
    private Date creationTime;
    @Getter
    @Setter
    private AuthorDto authorDto;

    public BlogDto() {
    }

    public BlogDto(long blogId, String title, String body, Date creationTime, AuthorDto authorDto) {
        this.blogId = blogId;
        this.title = title;
        this.body = body;
        this.creationTime = creationTime;
        this.authorDto = authorDto;
    }

    public BlogDto(String title, String body, Date creationTime, AuthorDto authorDto) {
        this.title = title;
        this.body = body;
        this.creationTime = creationTime;
        this.authorDto = authorDto;
    }
    
}
