/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.acl.AclManager;
import com.microservice.dao.ItemDAO;
import com.microservice.domain.Item;
import com.microservice.service.ItemService;
import com.microservice.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.stereotype.Service;

/**
 *
 * @author nabil
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private UserService userService;

    @Autowired
    private AclManager aclManager;

    /**
     *
     * @param item
     */
    @Override
    public void save(Item item) {
        itemDAO.save(item);
        aclManager.addPermission(Item.class, item.getId(), new PrincipalSid(userService.getCurrentAuthenticatedUser().getUser().getId().toString()), BasePermission.ADMINISTRATION);
    }

    /**
     *
     * @param item
     */
    @Override
    public void delete(Item item) {
        itemDAO.delete(item);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Item> findAllOrderById() {
        return itemDAO.findAllOrderById();

    }

    /**
     *
     * @param itemId
     * @return
     */
    @Override

    public Item findById(long itemId) {
        return itemDAO.findById(itemId);

    }

}
