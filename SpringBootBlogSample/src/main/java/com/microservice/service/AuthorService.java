/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.dto.AuthorDto;
import java.util.List;

/**
 *
 * @author nabil
 */
public interface AuthorService {

    /**
     
     * 
     * @param authorDto
     * @return 
     */
    public AuthorDto save(AuthorDto authorDto);

    List<AuthorDto> findAllOrderByNickNameAsc();
    
}
