package com.aurindo.delivery.emailService.resource;

import com.aurindo.delivery.emailService.model.EmailBody;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String PATH = "/emailservice/email";

    @Test
    public void whenPassABodyEmailShouldReturnAEmailCode() throws Exception {
        String createEmailUrl = new URL("http://localhost:" + port + PATH).toString();

        String sender = "sender@gmail.com";
        String recipient = "aurindo@gmail.com";
        String subtitle = "Subtitle test";
        String message = "It is only a test sender email";

        EmailBody emailBody = new EmailBody(sender, recipient, subtitle, message);
        HttpEntity<EmailBody> request = new HttpEntity<>(emailBody);

        ResponseEntity<String> response = restTemplate.postForEntity(createEmailUrl, request, String.class);

        TestCase.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
