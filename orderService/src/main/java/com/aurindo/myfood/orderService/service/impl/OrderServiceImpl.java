package com.aurindo.myfood.orderService.service.impl;

import com.aurindo.myfood.orderService.exception.OrderException;
import com.aurindo.myfood.orderService.helper.EmailHelper;
import com.aurindo.myfood.orderService.model.Customer;
import com.aurindo.myfood.orderService.model.Order;
import com.aurindo.myfood.orderService.model.OrderStatus;
import com.aurindo.myfood.orderService.repository.CustomerRepository;
import com.aurindo.myfood.orderService.repository.OrderRepository;
import com.aurindo.myfood.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailHelper emailHelper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order receiveOrder(final Order order) throws OrderException {
        order.setStatus(OrderStatus.RECEIVED);
        Order orderUpdated = orderRepository.save(order);

        Customer customer = customerRepository.findByCode(order.getUserCode()).get();

        CompletableFuture.runAsync(() -> {
            try {
                emailHelper.sendEmail(
                        customer.getEmail(),
                        "Subject Test",
                        "Order received " + orderUpdated.getCode());
                orderUpdated.setStatus(OrderStatus.PROCESSING);
                orderRepository.save(orderUpdated);
            } catch (Exception e) {
                e.printStackTrace();
                orderUpdated.setStatus(OrderStatus.ERROR);
                orderRepository.save(orderUpdated);
            }
        });

        return orderUpdated;
    }

    @Override
    public OrderStatus getStatus(String orderCode) throws OrderException {
        Order order = orderRepository.findByCode(orderCode).orElseThrow(
                () -> new OrderException("Order not found", orderCode)
        );
        return order.getStatus();
    }

}
