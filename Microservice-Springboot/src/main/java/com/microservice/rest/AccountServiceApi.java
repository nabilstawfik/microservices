/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.rest;

import com.microservice.exceptions.InvalidUUIDException;
import com.microservice.model.Account;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import com.microservice.service.AccountService;
import javax.ws.rs.FormParam;

/**
 *
 * @author nabil
 */
@Path("/account-service")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class AccountServiceApi {

    private static final Logger LOGGER = Logger.getLogger(AccountServiceApi.class.getName());

    @Autowired
    AccountService accountService;

    @POST
    @Path("/save-account")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveAccount(@FormParam("name") String name,@FormParam("email") String email) {
        try {
            Account account = accountService.save(name, email);
            return Response.ok(account).build();
            
        } catch (InvalidUUIDException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

}
