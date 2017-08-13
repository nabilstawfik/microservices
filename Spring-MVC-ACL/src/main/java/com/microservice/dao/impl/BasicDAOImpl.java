/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dao.impl;

import com.microservice.dao.BasicDAO;
import com.microservice.util.CollectionUtils;
import com.microservice.util.domain.Persistable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nabil
 * @param <T>
 */
@Repository("basicDAO")
@SuppressWarnings("unchecked")
public class BasicDAOImpl<T extends Persistable> implements BasicDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public T load(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }

    @Transactional
    public void delete(T t) {
        entityManager.remove(entityManager.merge(load((Class<T>) t.getClass(), t.getId())));
    }

    @Transactional
    public void persist(T t) {
        entityManager.persist(t);
    }

    @Transactional
    public T update(T t) {
        return entityManager.merge(t);
    }

    @Transactional
    public T createOrUpdate(T t) {
        if (t.isNew()) {
            persist(t);
        } else {
            update(t);
        }
        return t;
    }

    @Transactional
    public T getFirstResult(Query query) {
        List<T> result = CollectionUtils.<T>cast(query.getResultList());
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

}
