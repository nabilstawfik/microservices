/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.controller;

import com.microservice.dto.AuthorDto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.microservice.service.AuthorService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author nabil
 */
@RestController
@RequestMapping(path = "/api/author-service")
public class AuthorRestController {

    private static final Logger LOGGER = Logger.getLogger(AuthorRestController.class.getName());

    @Autowired
    AuthorService authorService;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<AuthorDto> save(@RequestBody AuthorDto authorDto) {
        try {
            authorDto = authorService.save(authorDto);
            authorDto.add(linkTo(methodOn(AuthorRestController.class).findAllOrderByNickNameAsc()).withSelfRel());
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/get-authors", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<AuthorDto>> findAllOrderByNickNameAsc() {
        try {
            List<AuthorDto> authorsList = authorService.findAllOrderByNickNameAsc();
            return new ResponseEntity<>(authorsList, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
