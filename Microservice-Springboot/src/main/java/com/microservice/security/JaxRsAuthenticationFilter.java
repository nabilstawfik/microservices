/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.security;

import com.microservice.config.SystemConfiguration;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author nabil
 */
@Provider
public class JaxRsAuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequest)
            throws WebApplicationException {

        String authCredentials = containerRequest
                .getHeaderString(SystemConfiguration.AUTHENTICATION_HDEADER_KEY_NAME);
        boolean authenticationStatus = SystemConfiguration.AUTHENTICATION_HDEADER_KEY_VALUE.equals(authCredentials);
        if (!authenticationStatus) {
            containerRequest.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//            throw new WebApplicationException(Status.UNAUTHORIZED);
        }

    }
}
