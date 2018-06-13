package com.marco.virtualstore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOG.info("Simulando Envio de Email!");
        LOG.info(mailMessage.toString());
        LOG.info("Email Enviado!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulando Envio de Email Html!");
        LOG.info(msg.toString());
        LOG.info("Email Enviado!");
    }
}
