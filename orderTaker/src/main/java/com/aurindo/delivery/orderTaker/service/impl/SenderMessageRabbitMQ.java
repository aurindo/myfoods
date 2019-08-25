package com.aurindo.delivery.orderTaker.service.impl;

import com.aurindo.delivery.orderTaker.model.InitialOrder;
import com.aurindo.delivery.orderTaker.service.SenderMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("senderRabbitMQ")
@Service
public class SenderMessageRabbitMQ implements SenderMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Override
    public void sendToQueue(final InitialOrder initialOrder) throws JsonProcessingException {
        ObjectMapper Obj = new ObjectMapper();
        String jsonInitialOrder = Obj.writeValueAsString(initialOrder);

        this.rabbitTemplate.convertAndSend(queue.getName(), jsonInitialOrder);
    }

}
