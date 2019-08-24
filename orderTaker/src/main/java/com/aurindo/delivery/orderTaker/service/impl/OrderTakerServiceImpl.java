package com.aurindo.delivery.orderTaker.service.impl;

import com.aurindo.delivery.orderTaker.model.InitialOrder;
import com.aurindo.delivery.orderTaker.service.OrderTakerService;
import com.aurindo.delivery.orderTaker.service.SenderMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderTakerServiceImpl implements OrderTakerService {

    @Autowired
    private SenderMessageService senderMessageService;

    @Override
    public String processOrder(final InitialOrder initialOrder) {
        initialOrder.setCode(UUID.randomUUID().toString());

        senderMessageService.sendToQueue(initialOrder);

        return initialOrder.getCode();
    }

}
