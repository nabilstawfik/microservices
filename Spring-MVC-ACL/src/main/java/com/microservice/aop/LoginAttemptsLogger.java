/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.aop;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

/**
 *
 * @author nabil
 */
@Aspect
@Service
public class LoginAttemptsLogger {

    private static final Logger LOGGER = Logger.getLogger(LoginAttemptsLogger.class.getName());

    @AfterReturning(pointcut = "execution(* com.microservice.security.SimpleUserDetailsService.loadUserByUsername(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("##############################################################");
        LOGGER.log(Level.INFO, "Intercepting Method : {0}", joinPoint.getSignature().getName());
        LOGGER.log(Level.INFO, "Method returned value is : {0}", result);
        LOGGER.info("##############################################################");

    }

    @AfterThrowing(pointcut = "execution(* com.microservice.security.SimpleUserDetailsService.loadUserByUsername(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        LOGGER.info("##############################################################");
        LOGGER.log(Level.INFO, "Intercepting Method : {0}", joinPoint.getSignature().getName());
        LOGGER.log(Level.INFO, "Method returned exception : {0}", error.getMessage());
        LOGGER.info("##############################################################");
    }
}
