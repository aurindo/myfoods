package com.aurindo.delivery.orderTaker.service.impl;

import com.aurindo.delivery.orderTaker.model.InitialOrder;
import com.aurindo.delivery.orderTaker.service.OrderTakerService;
import com.aurindo.delivery.orderTaker.service.SenderMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderTakerServiceImpl implements OrderTakerService {

    @Qualifier("senderRabbitMQ")
    @Autowired
    private SenderMessageService senderMessageService;

    @Override
    public String processOrder(final InitialOrder initialOrder) {
        initialOrder.setCode(UUID.randomUUID().toString());

        try {
            senderMessageService.sendToQueue(initialOrder);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return initialOrder.getCode();
    }

}
