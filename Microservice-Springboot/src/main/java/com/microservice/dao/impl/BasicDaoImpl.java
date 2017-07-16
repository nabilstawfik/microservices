/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nabil
 */
public class BasicDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    
}
