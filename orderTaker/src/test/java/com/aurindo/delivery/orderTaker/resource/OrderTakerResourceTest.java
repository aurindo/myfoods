package com.aurindo.delivery.orderTaker.resource;

import com.aurindo.delivery.orderTaker.model.InitialOrder;
import com.fasterxml.jackson.databind.JsonNode;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderTakerResourceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private Queue queue;

    private static final Pattern uuidValidation =
            Pattern.compile("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})");

    private final String PATH = "/ordertaker/order";

    @Before
    public void setup() {
        Mockito.when(queue.getName())
                .thenReturn("ordertaker");
    }

    @Test
    public void whenPassACorrectOrderShouldReturnNewOrderCode() throws Exception {
        String createOrderUrl = new URL("http://localhost:" + port + PATH).toString();

        List<String> items = new ArrayList<>();
        String address = "Torrens av, 123";
        String userCode = UUID.randomUUID().toString();

        InitialOrder initialOrder = new InitialOrder(items, address, userCode);
        HttpEntity<InitialOrder> request = new HttpEntity<>(initialOrder);

        ResponseEntity<String> response = restTemplate.postForEntity(createOrderUrl, request, String.class);

        TestCase.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        TestCase.assertTrue(uuidValidation.matcher(response.getBody()).matches());
    }

}
