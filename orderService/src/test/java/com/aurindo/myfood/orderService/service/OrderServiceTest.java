package com.aurindo.myfood.orderService.service;

import com.aurindo.myfood.orderService.helper.EmailHelper;
import com.aurindo.myfood.orderService.model.Order;
import com.aurindo.myfood.orderService.model.OrderStatus;
import com.aurindo.myfood.orderService.model.Product;
import com.aurindo.myfood.orderService.repository.OrderRepository;
import com.aurindo.myfood.orderService.service.impl.OrderServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OrderServiceImpl.class})
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private EmailHelper emailHelper;

    @Test
    public void whenReceiveAnCorrectOrderShouldReturnNothing() throws Exception {
        String code = "b61449b3-c5f3-4ac7-a60c-6b503357435d";
        List<Product> products = new ArrayList<>();
        String address = "Torrens Av";
        String userCode = "c72449b3-c5f3-4ac7-a60c-6b503357446e";

        Order order = new Order(code, products, address, userCode);
        Order newOrder = new Order(code, products, address, userCode, OrderStatus.RECEIVED);

        Mockito.when(orderRepository.save(order)).thenReturn(newOrder);

        Order savedOrder = orderService.receiveOrder(order);

        TestCase.assertNotNull(savedOrder);
        TestCase.assertEquals(OrderStatus.RECEIVED, savedOrder.getStatus());

        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
        Mockito.verify(emailHelper, Mockito.times(1)).sendEmail(
                "aurindo@gmail.com", "Subject Test", "Only a test message");
    }

}
