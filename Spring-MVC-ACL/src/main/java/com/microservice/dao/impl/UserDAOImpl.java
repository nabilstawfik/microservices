/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dao.impl;

import com.microservice.dao.UserDAO;
import com.microservice.domain.User;
import com.microservice.util.CollectionUtils;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nabil
 */
@Repository("userDAO")
public class UserDAOImpl extends BasicDAOImpl<User> implements UserDAO {

    @Override
    @Transactional
    public void save(User user) {
        createOrUpdate(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return super.update(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        super.delete(user);
    }

    @Override
    public User findById(Long id) {
        return getFirstResult(entityManager.createNamedQuery("User.findById").setParameter("id", id));
    }

    @Override
    public User findByEmail(String email) {
        return getFirstResult(entityManager.createNamedQuery("User.findByEmail").setParameter("email", email));
    }

    @Override
    public List<User> findAllOrderById() {
        return CollectionUtils.<User>cast(entityManager.createNamedQuery("User.findAllOrderById").getResultList());
    }

}
