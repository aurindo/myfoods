package com.aurindo.myfood.orderService.service;

import com.aurindo.myfood.orderService.exception.OrderException;
import com.aurindo.myfood.orderService.factory.OrderFactory;
import com.aurindo.myfood.orderService.model.Order;
import com.aurindo.myfood.orderService.model.Product;
import com.aurindo.myfood.orderService.repository.ProductRepository;
import com.aurindo.myfood.orderService.service.impl.ReceiverOrderRabbitMQ;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ReceiverOrderRabbitMQ.class, OrderFactory.class})
public class ReceiverOrderRabbitMQTest {

    @Qualifier("receiverRabbitMQ")
    @Autowired
    private ReceiverOrderService receiverOrderService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void whenReceiveACorrectStringShouldSaveAnOrder() throws OrderException {
        List<Product> products = new ArrayList<>();
        Product productA = new Product("name", new BigDecimal(10), 999);

        Mockito.when(productRepository.findByCode(productA.getCode())).thenReturn(productA);

        String message = "{\"code\":\"fd39ed1b-6c0c-4186-83a5-ac29e26e40e7\",\"items\":[\"" +
                productA.getCode() +
                "\"],\"addressDestination\":\"Torrens Av\",\"userCode\":\"1234-abcd-5678-efgh\"}";

        receiverOrderService.receiveMessage(message);

        Mockito.verify(productRepository, Mockito.times(1)).findByCode(productA.getCode());
    }

}
