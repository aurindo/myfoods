package com.aurindo.myfood.orderService.service;

import com.aurindo.myfood.orderService.exception.OrderException;
import com.aurindo.myfood.orderService.model.Order;
import com.aurindo.myfood.orderService.model.OrderStatus;

public interface OrderService {

    Order receiveOrder(Order order) throws OrderException;

    OrderStatus getStatus(String orderCode) throws OrderException;
}
