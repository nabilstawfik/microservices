/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.config.SystemConfiguration;
import com.microservice.dto.EmailMessageDto;
import com.microservice.dto.EmailMessageResponseDto;
import com.microservice.enums.EmailProviderEnum;
import com.microservice.service.EmailerService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author nabil
 */
class MailgunEmailerServiceImpl implements EmailerService {

    private static final Logger LOGGER = Logger.getLogger(MailgunEmailerServiceImpl.class.getName());

    @Override
    public EmailMessageResponseDto send(EmailMessageDto emailMessageDto) {
        EmailMessageResponseDto emailMessageResponseDto = new EmailMessageResponseDto(EmailProviderEnum.MAILGUN.getPrividerId());
        try {
            ClientConfig clientConfigMail = new ClientConfig();
            Client clientMail = ClientBuilder.newClient(clientConfigMail);
            clientMail.register(HttpAuthenticationFeature.basic("api", SystemConfiguration.EMAIL_MAILGUN_API_KEY));
            WebTarget targetMail = clientMail.target(SystemConfiguration.EMAIL_MAILGUN_URL);
            Form formData = new Form();
            formData.param("from", SystemConfiguration.EMAIL_SENDER_NAME + "<" + SystemConfiguration.EMAIL_SENDER_EMAIL + ">");
            formData.param("to", emailMessageDto.getTo());
            formData.param("subject", emailMessageDto.getSubject());
            formData.param("html", emailMessageDto.getBody());
            Response response = targetMail.request().post(Entity.entity(formData, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

            emailMessageResponseDto.setSuccess(response.getStatus() == 200);
            emailMessageResponseDto.setStatus(response.getStatusInfo().getReasonPhrase());
            LOGGER.log(Level.INFO, "Email sent with Mailgun to: {0} with subject:{1} send status={2}", new Object[]{emailMessageDto.getTo(), emailMessageDto.getSubject(), response.getStatus()});
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return emailMessageResponseDto;
    }
}
