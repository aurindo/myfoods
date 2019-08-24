package com.aurindo.delivery.orderTaker.resource;

import com.aurindo.delivery.orderTaker.model.InitialOrder;
import com.aurindo.delivery.orderTaker.service.OrderTakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
public class OrderTakerResource {

    @Autowired
    private OrderTakerService orderTakerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addOrder(@RequestBody final InitialOrder initialOrder, HttpServletResponse response) {
        String orderCode = orderTakerService.processOrder(initialOrder);

        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + orderCode).toUriString());
        return new ResponseEntity<>(orderCode, HttpStatus.CREATED);
    }

}
