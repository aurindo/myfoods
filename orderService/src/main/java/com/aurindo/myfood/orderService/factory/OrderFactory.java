package com.aurindo.myfood.orderService.factory;

import com.aurindo.myfood.orderService.exception.OrderException;
import com.aurindo.myfood.orderService.model.Order;
import com.aurindo.myfood.orderService.model.OrderStatus;
import com.aurindo.myfood.orderService.model.Product;
import com.aurindo.myfood.orderService.repository.ProductRepository;
import com.aurindo.myfood.orderService.util.JSonWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderFactory {

    @Autowired
    private ProductRepository productRepository;

    public Order create(String message) throws OrderException {

        try {
            JsonNode jsonpObject = JSonWrapper.getInstance().stringToJSonObject(message);
            String code = jsonpObject.get("code").textValue();
            String address = jsonpObject.get("addressDestination").textValue();
            String userCode = jsonpObject.get("userCode").textValue();

            List<Product> products = new ArrayList<>();
            jsonpObject.get("items").elements().forEachRemaining(productCode -> {
                products.add(productRepository.findByCode(productCode.textValue()));
            });

            return new Order(code, products, address, userCode);

        } catch (IOException e) {
            e.printStackTrace();
            throw new OrderException("Error creating order", e);
        }

    }

}
