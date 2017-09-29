/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.dto.BlogDto;
import com.microservice.exception.InvalidAuthorException;
import java.util.List;

/**
 *
 * @author nabil
 */
public interface BlogService {

    List<BlogDto> findAllByOrderByCreationTimeDesc(int page, int size);

    BlogDto save(BlogDto blogDto) throws InvalidAuthorException;

}
