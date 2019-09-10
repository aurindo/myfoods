package com.aurindo.myfood.orderService.repository;

import com.aurindo.myfood.orderService.model.Order;
import com.aurindo.myfood.orderService.model.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findByCode(String code);
}
