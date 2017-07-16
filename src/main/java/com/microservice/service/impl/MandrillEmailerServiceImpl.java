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
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nabil
 */
class MandrillEmailerServiceImpl implements EmailerService {

    private static final Logger LOGGER = Logger.getLogger(MandrillEmailerServiceImpl.class.getName());

    @Override
    public EmailMessageResponseDto send(EmailMessageDto emailMessageDto) {

        EmailMessageResponseDto emailMessageResponseDto = new EmailMessageResponseDto(EmailProviderEnum.MANDRILL.getPrividerId());
        try {
            MandrillApi mandrillApi = new MandrillApi(SystemConfiguration.EMAIL_MANDRILL_API_KEY);
            MandrillMessage message = new MandrillMessage();
            message.setSubject(emailMessageDto.getSubject());
            message.setHtml(emailMessageDto.getBody());
            message.setAutoText(true);
            message.setFromEmail(SystemConfiguration.EMAIL_SENDER_EMAIL);
            message.setFromName(SystemConfiguration.EMAIL_SENDER_NAME);
            ArrayList<Recipient> recipients = new ArrayList<>();
            Recipient recipient = new Recipient();
            recipient.setEmail(emailMessageDto.getTo());

            recipients.add(recipient);

            message.setTo(recipients);
            message.setPreserveRecipients(true);

            MandrillMessageStatus[] messageStatusReports = mandrillApi
                    .messages().send(message, false);
            String messageSendStatus = null;
            if (messageStatusReports != null) {
                messageSendStatus = messageStatusReports[0].getStatus();
            }
            emailMessageResponseDto.setSuccess("sent".equals(messageSendStatus));
            emailMessageResponseDto.setStatus(messageSendStatus);
            LOGGER.log(Level.INFO, "Email sent with Mandrill to: {0} with subject:{1} send status={2}", new Object[]{emailMessageDto.getTo(), emailMessageDto.getSubject(), messageSendStatus});
        } catch (MandrillApiError | IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return emailMessageResponseDto;
    }

}
