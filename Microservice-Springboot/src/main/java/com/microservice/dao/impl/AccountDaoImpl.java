/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dao.impl;

import com.microservice.model.Account;
import com.microservice.dao.AccountDao;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nabil
 */
@Transactional
@Repository("accountDao")
public class AccountDaoImpl extends BasicDaoImpl implements AccountDao {

    @Override
    public Account save(Account account) {
        if (account.getId() == 0) {
            getEntityManager().persist(account);
        } else {
            account = getEntityManager().merge(account);
        }
        return account;
    }
}
