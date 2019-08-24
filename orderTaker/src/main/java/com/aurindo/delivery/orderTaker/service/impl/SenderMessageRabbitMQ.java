package com.aurindo.delivery.orderTaker.service.impl;

import com.aurindo.delivery.orderTaker.model.InitialOrder;
import com.aurindo.delivery.orderTaker.service.SenderMessageService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderMessageRabbitMQ implements SenderMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Override
    public void sendToQueue(final InitialOrder initialOrder) {
        this.rabbitTemplate.convertAndSend(queue.getName(), initialOrder);
    }

}
