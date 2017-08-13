/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author nabil
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private SimpleUserDetailsService simpleUserDetailsService;
    
    public LoginAuthenticationProvider() {
        setPasswordEncoder(new BCryptPasswordEncoder());
        setUserDetailsService(simpleUserDetailsService);

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            Authentication auth = super.authenticate(authentication);
            return auth;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);
    }


}
