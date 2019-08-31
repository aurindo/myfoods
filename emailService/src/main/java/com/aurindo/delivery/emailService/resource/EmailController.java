package com.aurindo.delivery.emailService.resource;

import com.aurindo.delivery.emailService.model.EmailBody;
import com.aurindo.delivery.emailService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addOrder(@RequestBody final EmailBody emailBody, HttpServletResponse response) {
        emailService.send(emailBody);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
