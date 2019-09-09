package com.aurindo.myfood.orderService.resource;

import com.aurindo.myfood.orderService.model.Customer;
import com.aurindo.myfood.orderService.model.Product;
import com.aurindo.myfood.orderService.service.CustomerService;
import com.aurindo.myfood.orderService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addProduct(@RequestBody final Product product, HttpServletResponse response) {
        String productCode = productService.addProduct(product);

        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + productCode).toUriString());
        return new ResponseEntity<>(productCode, HttpStatus.CREATED);
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public Iterable<Product> findAll() {
        Iterable<Product> products = productService.findAll();
        return products;
    }

}
