package com.marco.virtualstore.services;

import com.marco.virtualstore.domains.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage mailMessage = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(mailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(pedido.getCliente().getEmail());
        mailMessage.setFrom(sender);
        mailMessage.setSubject("Pedido Confirmado! Código: "+ pedido.getId());
        mailMessage.setSentDate(new Date(System.currentTimeMillis()));
        mailMessage.setText(pedido.toString());
        return mailMessage;
    }

    //Utilizando o thymeleaf
    protected String htmlFromTemplatePedido(Pedido pedido){
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return this.templateEngine.process("email/confirmacaoPedido", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(mm);
        } catch (MessagingException e){
            sendOrderConfirmationEmail(pedido);
        }
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(pedido.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido Confirmado! Código: "+ pedido.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(pedido), true);
        return mimeMessage;
    }
}
