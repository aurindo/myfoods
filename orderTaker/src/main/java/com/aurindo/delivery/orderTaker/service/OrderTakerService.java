package com.aurindo.delivery.orderTaker.service;

import com.aurindo.delivery.orderTaker.model.InitialOrder;

public interface OrderTakerService {

    String processOrder(final InitialOrder initialOrder);

}
