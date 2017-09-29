/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author nabil
 */
public class AuthorDto extends ResourceSupport {

    @Getter
    @Setter
    private long authorId;
    @Getter
    @Setter
    private String nickName;

    public AuthorDto() {
    }

    public AuthorDto(long authorId, String nickName) {
        this.authorId = authorId;
        this.nickName = nickName;
    }

    public AuthorDto(String nickName) {
        this.nickName = nickName;
    }
    

}
