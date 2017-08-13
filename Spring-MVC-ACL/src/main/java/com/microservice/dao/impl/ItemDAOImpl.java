/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dao.impl;

import com.microservice.dao.ItemDAO;
import com.microservice.domain.Item;
import com.microservice.util.CollectionUtils;
import com.microservice.util.SystemConfiguration;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nabil
 */
@Repository("itemDAO")
public class ItemDAOImpl extends BasicDAOImpl<Item> implements ItemDAO {

    /**
     *
     * @param item
     */
    @Override
    @Transactional
    @Secured(SystemConfiguration.AUTHENTICATED_USER_ROLE)
    public void save(Item item) {
        createOrUpdate(item);
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    @Secured(SystemConfiguration.AUTHENTICATED_USER_ROLE)
    public Item update(Item item) {
        return super.update(item);
    }

    /**
     *
     * @param item
     */
    @Override
    @Transactional
    @Secured(SystemConfiguration.AUTHENTICATED_USER_ROLE)
    @PreAuthorize("hasPermission(#item, 'administration')")
    public void delete(Item item) {
        super.delete(item);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    @Secured(SystemConfiguration.AUTHENTICATED_USER_ROLE)
    @PostAuthorize("hasPermission(returnObject, 'administration')")
    public Item findById(Long id) {
        return getFirstResult(entityManager.createNamedQuery("Item.findById").setParameter("id", id));
    }

    /**
     * Find all the items by current user id as Sid and has permission administration.
     * @return Items by user.id as Sid
     */
    @Override
    @Secured(SystemConfiguration.AUTHENTICATED_USER_ROLE)
    @PostFilter("hasPermission(filterObject, 'administration')")
    public List<Item> findAllOrderById() {
        return CollectionUtils.<Item>cast(entityManager.createNamedQuery("Item.findAllOrderById").getResultList());
    }

}
