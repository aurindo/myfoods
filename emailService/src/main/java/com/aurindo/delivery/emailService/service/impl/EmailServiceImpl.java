package com.aurindo.delivery.emailService.service.impl;

import com.aurindo.delivery.emailService.model.EmailBody;
import com.aurindo.delivery.emailService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(EmailBody emailBody) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailBody.getRecipient());

        msg.setSubject(emailBody.getSubject());
        msg.setText(emailBody.getMessage());

        javaMailSender.send(msg);
    }
}
