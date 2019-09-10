package com.aurindo.myfood.orderService.exception;

public class OrderException extends Exception {

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Exception e) {
        super(message, e);
    }

    public OrderException(String message, String orderCode) {
        super(message + ": " + orderCode);
    }
}
