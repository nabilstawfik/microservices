/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.security;

import com.microservice.dao.UserDAO;
import com.microservice.domain.User;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author nabil
 */
@Component("simpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    /**
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(MessageFormat.format("Username : {0} not found.", email));
        }
        return new CustomUserDetails(user, getGrantedAuthorities(user));
    }

    /**
     *
     * @param user
     * @return
     */
    public Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        return user.getAccountRoleList().stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toCollection(ArrayList::new));
    }

}
