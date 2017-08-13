/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.dao.UserDAO;
import com.microservice.domain.User;
import com.microservice.domain.UserRole;
import com.microservice.security.CustomUserDetails;
import com.microservice.util.SystemConfiguration;
import com.microservice.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author nabil
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User register(String email, String password) {

        if (userDAO.findByEmail(email) == null) {
            User user = new User(email);
            List<UserRole> roles = new ArrayList<>();
            roles.add(new UserRole(SystemConfiguration.AUTHENTICATED_USER_ROLE, user));
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setAccountRoleList(roles);
            user.setEnabled(true);
            userDAO.save(user);
            return user;
        }
        return null;
    }

    @Override
    public User updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = userDAO.findById(userId);
        if (user != null && user.getPassword().equals(new BCryptPasswordEncoder().encode(oldPassword))) {
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userDAO.save(user);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public List<User> findAllOrderById() {
        return userDAO.findAllOrderById();
    }

    @Override
    public void delete(Long id) {
        userDAO.delete(findById(id));
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public User update(User user) {
        return userDAO.update(user);
    }

    @Override
    public CustomUserDetails getCurrentAuthenticatedUser() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
