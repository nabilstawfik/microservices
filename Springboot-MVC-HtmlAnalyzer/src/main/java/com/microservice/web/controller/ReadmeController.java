/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReadmeController {

    /** Display the readme page.
     *
     * @return
     */
    @GetMapping("/readme")
    public ModelAndView showHome() {
        return new ModelAndView("readme");
    }

}
