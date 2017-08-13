/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice;

import com.microservice.security.CustomUserDetails;
import com.microservice.security.SimpleUserDetailsService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author nabil
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/spring/applicationMvc.xml", "classpath:/META-INF/spring/applicationContext.xml",
    "classpath:/META-INF/spring/applicationPersistence.xml", "classpath:/META-INF/spring/applicationSecurity.xml",
    "classpath:/META-INF/spring/applicationAcl.xml"})
//@WebAppConfiguration
public abstract class AbstractSecurityTest {

    @Autowired
    SimpleUserDetailsService simpleUserDetailsService;

    public void setAuthentication(String username) {
        CustomUserDetails user = (CustomUserDetails) simpleUserDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
