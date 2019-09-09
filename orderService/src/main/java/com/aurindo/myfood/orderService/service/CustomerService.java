package com.aurindo.myfood.orderService.service;

import com.aurindo.myfood.orderService.model.Customer;

public interface CustomerService {
    String addCustomer(Customer customer);

    Iterable<Customer> findAll();
}