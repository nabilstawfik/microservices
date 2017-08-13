/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.web.controller;

import com.microservice.domain.User;
import com.microservice.dto.UserDto;
import com.microservice.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nabil
 */
@Controller
public class RegisterController {

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());

    @Autowired
    UserService userService;

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("userDto", new UserDto());
        return mav;
    }

    /**
     *
     * @param request
     * @param response
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistration(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("userDto") UserDto userDto) {
        ModelAndView model = new ModelAndView("redirect:/login");
        try {
            User user = userService.register(userDto.getEmail(), userDto.getPassword());
        } catch (Exception e) {
            model.setViewName("register?error");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return model;
    }
}
