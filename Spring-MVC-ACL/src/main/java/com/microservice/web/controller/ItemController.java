/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.web.controller;

import com.microservice.domain.Item;
import com.microservice.dto.ItemDto;
import com.microservice.service.ItemService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author nabil
 */
@Controller
public class ItemController {

    private static final Logger LOGGER = Logger.getLogger(ItemController.class.getName());
    @Autowired
    ItemService itemService;

//    Add new Item

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add-item", method = RequestMethod.GET)
    public ModelAndView addItem(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("item/add-item");
        mav.addObject("itemDto", new ItemDto());
        return mav;
    }

    /**
     *
     * @param request
     * @param response
     * @param itemDto
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/add-item", method = RequestMethod.POST)
    public ModelAndView processAddItem(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("itemDto") ItemDto itemDto,final RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView("redirect:/list-items");
        try {
            itemService.save(new Item(itemDto.getName(), itemDto.getPrice()));
            redirectAttributes.addFlashAttribute("success", "Item added successfully.");
        } catch (Exception e) {
            model.setViewName("/add-item?error");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return model;
    }

//    Item Listing

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/list-items", method = RequestMethod.GET)
    public ModelAndView listItems(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("item/list-items");
        try {
            model.addObject("items", itemService.findAllOrderById());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return model;
    }

    /**
     *
     * @param request
     * @param response
     * @param itemId
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/delete-item", method = RequestMethod.GET)
    public ModelAndView deleteItem(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="itemId",required = true) Long itemId,
            final RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView("redirect:/list-items");
        try {
            itemService.delete(itemService.findById(itemId));
            redirectAttributes.addFlashAttribute("success", "Item deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "You don't have permission to delete this item.");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return model;
    }

//    Show Item

    /**
     *
     * @param request
     * @param response
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/show-item", method = RequestMethod.GET)
    public ModelAndView showItem(HttpServletRequest request, HttpServletResponse response, @RequestParam("itemId") Long itemId) {
        ModelAndView model = new ModelAndView("item/show-item");
        try {
            model.addObject("item", itemService.findById(itemId));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return model;
    }
}
