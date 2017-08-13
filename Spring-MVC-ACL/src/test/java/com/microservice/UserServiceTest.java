/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice;

import com.microservice.domain.User;
import com.microservice.service.UserService;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author nabil
 */
public class UserServiceTest extends AbstractSecurityTest {

    @Autowired
    private UserService userService;
    private User user;

    @Before
    public void setup() {
        user = userService.register("nabilstawfik@gmail.com", "123456");
    }

    @After
    public void tearDown() {
        userService.delete(user.getId());
    }

    @Test
    public void checkRegister() {
        Assert.assertNotEquals(user, null);
    }

    @Test
    public void findById() {
        Assert.assertEquals(user.getId(), userService.findById(user.getId()).getId());
    }

    @Test
    public void findByEmail() {
        Assert.assertEquals("nabilstawfik@gmail.com", userService.findByEmail(user.getEmail()).getEmail());
    }

    @Test
    public void findAllOrderById() {
        userService.register("nabilstawfik1@gmail.com", "123456");
        userService.register("nabilstawfik2@gmail.com", "123456");
        userService.register("nabilstawfik3@gmail.com", "123456");
        List<User> users = userService.findAllOrderById();
        Assert.assertEquals(4, users.size());
    }

    @Test
    public void deleteUser() {
        User toDelete = userService.register("nabilstawfik6@gmail.com", "123456");
        userService.delete(toDelete.getId());
        toDelete = userService.findByEmail("nabilstawfik6@gmail.com");
        Assert.assertEquals(toDelete, null);
    }

}
