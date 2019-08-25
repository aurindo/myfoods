package com.aurindo.myfood.orderService.repository;

import com.aurindo.myfood.orderService.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByCode(String code);
}

