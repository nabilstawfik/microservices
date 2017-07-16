/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dao;

import com.microservice.model.Account;

/**
 *
 * @author nabil
 */
public interface AccountDao {

    Account save(Account account);
    
}
