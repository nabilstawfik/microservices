/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.repository;

import com.microservice.model.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author nabil
 */
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

}
