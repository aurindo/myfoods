package com.aurindo.myfood.orderService.resource;

import com.aurindo.myfood.orderService.model.OrderStatus;
import com.aurindo.myfood.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderCode}")
    public ResponseEntity<String> getOrderStatus(
            @PathVariable(value = "orderCode", required = true) String orderCode) throws Exception {
        OrderStatus orderStatus = orderService.getStatus(orderCode);

        return new ResponseEntity<>(orderStatus.name(), HttpStatus.OK);
    }
}
