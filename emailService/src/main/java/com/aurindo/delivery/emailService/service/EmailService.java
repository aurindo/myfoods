package com.aurindo.delivery.emailService.service;

import com.aurindo.delivery.emailService.model.EmailBody;

public interface EmailService {
    void send(EmailBody emailBody);
}
