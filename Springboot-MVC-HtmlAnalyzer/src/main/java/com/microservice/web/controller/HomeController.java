/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.web.controller;

import com.microservice.dto.PageAnalysisDto;
import com.microservice.service.HtmlAnalyzer;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    @Autowired
    HtmlAnalyzer htmlAnalyzer;

    /**
     * Display the landing page.
     *
     * @return
     */
    @GetMapping("/")
    public ModelAndView showHome() {
        return new ModelAndView("index");
    }

    /**
     * Process the form submit on landing page to conduct the web page analysis.
     *
     * @param request
     * @param response
     * @param model
     * @param pageURL
     * @return
     */
    @PostMapping(value = "/")
    public String conductWebPageAnalysis(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model,
            @RequestParam(value = "pageURL", required = true) String pageURL) {
        try {
            PageAnalysisDto pageAnalysisDto = htmlAnalyzer.conductWebPageAnalysis(pageURL);
            model.put("pageAnalysisDto", pageAnalysisDto);
            model.put("pageURL", pageURL);
        } catch (Exception e) {
            model.put("errorMsg", "Can't fetch analytics for the given url.");
            LOGGER.log(Level.SEVERE, null, e);
        }
        return "index";
    }
}
