package com.aurindo.myfood.orderService.service.impl;

import com.aurindo.myfood.orderService.model.Customer;
import com.aurindo.myfood.orderService.repository.CustomerRepository;
import com.aurindo.myfood.orderService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String addCustomer(final Customer customer) {
        return customerRepository.save(customer).getCode();
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }
}
