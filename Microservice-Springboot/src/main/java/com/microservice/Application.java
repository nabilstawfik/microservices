/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice;

import com.microservice.config.JerseyConfig;
import com.microservice.rest.AccountServiceApi;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.microservice.dao.AccountDao;
import com.microservice.service.AccountService;

/**
 *
 * @author nabil
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {AccountService.class,AccountDao.class,AccountServiceApi.class})
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/microservice/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        return registration;
    }

}
