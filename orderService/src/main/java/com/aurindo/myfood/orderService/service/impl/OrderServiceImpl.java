package com.aurindo.myfood.orderService.service.impl;

import com.aurindo.myfood.orderService.exception.OrderException;
import com.aurindo.myfood.orderService.helper.EmailHelper;
import com.aurindo.myfood.orderService.model.Order;
import com.aurindo.myfood.orderService.model.OrderStatus;
import com.aurindo.myfood.orderService.repository.OrderRepository;
import com.aurindo.myfood.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailHelper emailHelper;

    @Override
    public Order receiveOrder(final Order order) throws OrderException {
        order.setStatus(OrderStatus.RECEIVED);
        Order orderUpdated = orderRepository.save(order);

        emailHelper.sendEmail("aurindo@gmail.com", "Subject Test", "Only a test message");

        return orderUpdated;
    }


}
