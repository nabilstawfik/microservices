/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.rest;

import com.microservice.dto.EmailMessageDto;
import com.microservice.dto.EmailMessageResponseDto;
import com.microservice.exceptions.InvalidEmailAddressException;
import com.microservice.service.EmailServiceManager;
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

/**
 *
 * @author nabil
 */
@Path("/emailer")
@Component
public class EmailServiceApi {

    private static final Logger LOGGER = Logger.getLogger(EmailServiceApi.class.getName());

    @Autowired
    EmailServiceManager emailServiceManager;

    @POST
    @Path("/send")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(EmailMessageDto emailMessageDto) {
        try {
            EmailMessageResponseDto emailMessageResponseDto = emailServiceManager.sendEmail(emailMessageDto);
            return Response.ok(emailMessageResponseDto).build();
        } catch (InvalidEmailAddressException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

}
