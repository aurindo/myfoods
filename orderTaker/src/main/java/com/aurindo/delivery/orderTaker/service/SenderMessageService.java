package com.aurindo.delivery.orderTaker.service;

import com.aurindo.delivery.orderTaker.model.InitialOrder;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SenderMessageService {

    void sendToQueue(final InitialOrder initialOrder) throws JsonProcessingException;

}
