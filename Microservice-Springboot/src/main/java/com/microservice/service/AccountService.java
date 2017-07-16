/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.dto.InstanceDto;
import com.microservice.exceptions.InvalidUUIDException;
import com.microservice.model.Account;

/**
 *
 * @author nabil
 */
public interface AccountService {

    /**
     * Create new instance with the instance aggregate UUID.
     *
     * @param uuidString
     * @return instanceDto
     * @throws InvalidUUIDException
     */
    public Account save(String name, String email) throws InvalidUUIDException;
    
}
