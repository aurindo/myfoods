package com.aurindo.myfood.orderService.service.impl;

import com.aurindo.myfood.orderService.model.Product;
import com.aurindo.myfood.orderService.repository.ProductRepository;
import com.aurindo.myfood.orderService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addProduct(final Product product) {
        return productRepository.save(product).getCode();
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
}
