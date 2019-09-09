package com.aurindo.myfood.orderService.resource;

import com.aurindo.myfood.orderService.model.Customer;
import com.aurindo.myfood.orderService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/customer")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addCustomer(@RequestBody final Customer customer, HttpServletResponse response) {
        String customerCode = customerService.addCustomer(customer);

        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + customerCode).toUriString());
        return new ResponseEntity<>(customerCode, HttpStatus.CREATED);
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public Iterable<Customer> findAll() {
        Iterable<Customer> customers = customerService.findAll();
        return customers;
    }

}
