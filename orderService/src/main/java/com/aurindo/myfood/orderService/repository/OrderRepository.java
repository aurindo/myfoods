package com.aurindo.myfood.orderService.repository;

import com.aurindo.myfood.orderService.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {}
