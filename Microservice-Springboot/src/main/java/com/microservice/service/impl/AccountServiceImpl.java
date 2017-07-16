/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.exceptions.InvalidUUIDException;
import com.microservice.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.dao.AccountDao;
import com.microservice.service.AccountService;

/**
 *
 * @author nabil
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account save(String name, String email) throws InvalidUUIDException {

        Account account = new Account(name, email);
        return accountDao.save(account);
    }

}
