/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice;

import com.microservice.domain.Item;
import com.microservice.domain.User;
import com.microservice.service.ItemService;
import com.microservice.service.UserService;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

/**
 *
 * @author nabil
 */
public class ItemServiceTest extends AbstractSecurityTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    private User user1;
    private User user2;

    @Before
    public void setup() {
        user1 = userService.register("nabilstawfik@gmail.com", "123456");
        user2 = userService.register("nabil@gmail.com", "123456");
    }

    @After
    public void tearDown() {
        userService.delete(user1.getId());
        userService.delete(user2.getId());
    }

    @Test
    public void addItemWithoutAuthentication() {
        try {
            itemService.save(new Item("Item 1", 20));
            Assert.assertEquals(new Item(), null);
        } catch (AuthenticationCredentialsNotFoundException e) {

        }
    }

    @Test
    public void addItemWithAuthentication() {
        try {
            setAuthentication("nabilstawfik@gmail.com");
            Item item = new Item("Item 1", 20);
            itemService.save(item);
            Assert.assertNotEquals(item.getId(), null);
            clearAuthentication();
        } catch (AuthenticationCredentialsNotFoundException e) {
            Assert.assertEquals(1, 2);
        }
    }

    @Test
    public void findById() {
        try {
            setAuthentication("nabilstawfik@gmail.com");
            Item item = new Item("Item 1", 20);
            itemService.save(item);
            item = itemService.findById(item.getId());
            Assert.assertNotEquals(item.getId(), null);
        } catch (AccessDeniedException e) {
            Assert.assertEquals(1, 2);
        }
        clearAuthentication();
    }

    @Test
    public void findByIdAnonymous() {
        try {
            setAuthentication("nabilstawfik@gmail.com");
            Item item = new Item("Item 1", 20);
            itemService.save(item);
            clearAuthentication();
            itemService.findById(item.getId());
            Assert.assertEquals(2, 1);
        } catch (AuthenticationCredentialsNotFoundException e) {
        }
    }

    @Test
    public void findItemOfOtherUser() {
        try {
            setAuthentication("nabilstawfik@gmail.com");
            Item item = new Item("Item 1", 20);
            itemService.save(item);
            setAuthentication("nabil@gmail.com");
            itemService.findById(item.getId());
            Assert.assertEquals(1, 2);
        } catch (AccessDeniedException e) {
        }
        clearAuthentication();
    }

    @Test
    public void findAllOrderByIdAnonymous() {
        try {
            itemService.findAllOrderById();
            Assert.assertEquals(2, 1);
        } catch (AuthenticationCredentialsNotFoundException e) {
        }
    }

    @Test
    public void findAllOrderById() {
        try {
            setAuthentication("nabilstawfik@gmail.com");
            Item item1 = new Item("Item 1", 20);
            Item item2 = new Item("Item 2", 40);
            itemService.save(item1);
            itemService.save(item2);
            List<Item> items = itemService.findAllOrderById();
            Assert.assertEquals(2, items.size());
        } catch (AccessDeniedException e) {
        }
        clearAuthentication();
    }

    @Test
    public void findAllOrderByIdOtherUser() {
        try {
            setAuthentication("nabil@gmail.com");
            Item item = new Item("Item 1", 20);
            itemService.save(item);

            setAuthentication("nabilstawfik@gmail.com");
            Item item1 = new Item("Item 1", 20);
            Item item2 = new Item("Item 2", 40);
            itemService.save(item1);
            itemService.save(item2);
            List<Item> items = itemService.findAllOrderById();
            Assert.assertEquals(2, items.size());

            setAuthentication("nabil@gmail.com");
            items = itemService.findAllOrderById();
            Assert.assertEquals(1, items.size());

        } catch (AccessDeniedException e) {
        }
        clearAuthentication();
    }

}
