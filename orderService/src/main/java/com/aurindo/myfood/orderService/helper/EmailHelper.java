package com.aurindo.myfood.orderService.helper;

import com.aurindo.myfood.orderService.exception.OrderException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class EmailHelper {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PATH = "/emailservice/email";

    public void sendEmail(String recipient, String subject, String message) throws OrderException {
        int port = 8081;

        String createEmailUrl;
        try {
            createEmailUrl = new URL("http://localhost:" + port + PATH).toString();
        } catch (MalformedURLException mue) {
            throw new OrderException("Error sending e-mail.", mue);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode emailBody = objectMapper.createObjectNode();
        emailBody.put("recipient", recipient);
        emailBody.put("subject", subject);
        emailBody.put("message", message);

        HttpEntity<ObjectNode> request = new HttpEntity<>(emailBody);

        ResponseEntity<String> response = restTemplate.postForEntity(createEmailUrl, request, String.class);
    }

}
