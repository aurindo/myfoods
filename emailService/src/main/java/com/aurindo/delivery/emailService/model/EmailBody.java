package com.aurindo.delivery.emailService.model;

import java.util.UUID;

public class EmailBody {

    private String code;
    private String sender;
    private String recipient;
    private String subject;
    private String message;

    public EmailBody() {}

    public EmailBody(String sender, String recipient, String subject, String message) {
        this.code = UUID.randomUUID().toString();
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
