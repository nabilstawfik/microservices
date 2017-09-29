/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.controller;

import com.microservice.dto.BlogDto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.service.BlogService;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nabil
 */
@RestController
@RequestMapping(path = "/api/blog-service")
public class BlogRestController {

    private static final Logger LOGGER = Logger.getLogger(BlogRestController.class.getName());

    @Autowired
    BlogService blogService;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<BlogDto> save(@RequestBody BlogDto blogDto) {
        try {
            blogDto = blogService.save(blogDto);
            blogDto.add(linkTo(methodOn(BlogRestController.class).update(blogDto)).withSelfRel());
            blogDto.add(linkTo(methodOn(BlogRestController.class).findAllByOrderByCreationTimeDesc(0, 20)).withSelfRel());
            return new ResponseEntity<>(blogDto, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<BlogDto> update(@RequestBody BlogDto blogDto) {
        try {
            blogDto = blogService.save(blogDto);
            blogDto.add(linkTo(methodOn(BlogRestController.class).findAllByOrderByCreationTimeDesc(0, 20)).withSelfRel());
            return new ResponseEntity<>(blogDto, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/get-blogs/{page}/{size}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<BlogDto>> findAllByOrderByCreationTimeDesc(
            @PathVariable(name = "page", required = true) Integer page,
            @PathVariable(name = "size", required = true) Integer size) {
        try {
            List<BlogDto> blogsList = blogService.findAllByOrderByCreationTimeDesc(page, size);
            blogsList.stream().forEachOrdered(blogDto -> {
                blogDto.add(linkTo(methodOn(BlogRestController.class).update(blogDto)).withSelfRel());
            });
            return new ResponseEntity<>(blogsList, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
