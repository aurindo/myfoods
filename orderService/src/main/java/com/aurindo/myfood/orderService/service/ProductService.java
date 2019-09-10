package com.aurindo.myfood.orderService.service;

import com.aurindo.myfood.orderService.model.Product;

public interface ProductService {
    String addProduct(Product product);

    Iterable<Product> findAll();
}