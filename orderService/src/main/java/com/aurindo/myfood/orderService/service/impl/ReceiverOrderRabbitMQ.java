package com.aurindo.myfood.orderService.service.impl;

import com.aurindo.myfood.orderService.exception.OrderException;
import com.aurindo.myfood.orderService.factory.OrderFactory;
import com.aurindo.myfood.orderService.model.Order;
import com.aurindo.myfood.orderService.service.OrderService;
import com.aurindo.myfood.orderService.service.ReceiverOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("receiverRabbitMQ")
@Service
public class ReceiverOrderRabbitMQ implements ReceiverOrderService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFactory orderFactory;

    @Override
    @RabbitListener(queues = "ordertaker")
    public void receiveMessage(String message) throws OrderException {
        Order order = orderFactory.create(message);
        orderService.receiveOrder(order);
    }

}
