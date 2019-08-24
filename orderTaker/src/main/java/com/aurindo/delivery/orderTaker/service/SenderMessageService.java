package com.aurindo.delivery.orderTaker.service;

import com.aurindo.delivery.orderTaker.model.InitialOrder;

public interface SenderMessageService {

    void sendToQueue(final InitialOrder initialOrder);

}
