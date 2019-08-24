package com.aurindo.delivery.orderTaker.service;

import com.aurindo.delivery.orderTaker.model.InitialOrder;
import com.aurindo.delivery.orderTaker.service.impl.OrderTakerServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OrderTakerServiceImpl.class})
public class OrderTakerServiceTest {

    private static final Pattern uuidValidation =
            Pattern.compile("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})");

    @Autowired
    private OrderTakerService orderTakerService;

    @Test
    public void whenreceiveAcorrectORderShouldReturnNewOrderCode() {
        List<String> items = new ArrayList<>();
        String address = "Torrens av, 123";
        String userCode = UUID.randomUUID().toString();

        InitialOrder initialOrder = new InitialOrder(items, address, userCode);
        String orderCode = orderTakerService.processOrder(initialOrder);

        TestCase.assertNotNull(orderCode);
        TestCase.assertTrue(uuidValidation.matcher(orderCode).matches());
    }

}
