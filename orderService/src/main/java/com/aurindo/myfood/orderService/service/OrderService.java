package com.aurindo.myfood.orderService.service;

import com.aurindo.myfood.orderService.exception.OrderException;
import com.aurindo.myfood.orderService.model.Order;

public interface OrderService {

    Order receiveOrder(Order order) throws OrderException;

}
