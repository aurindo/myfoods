package com.aurindo.myfood.orderService.service;

import com.aurindo.myfood.orderService.exception.OrderException;

public interface ReceiverOrderService {

    void receiveMessage(String message) throws OrderException;

}
