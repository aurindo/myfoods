package com.aurindo.delivery.orderTaker.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class InitialOrder implements Serializable {

    private String code;
    private List<String> items;
    private String addressDestination;
    private String userCode;

    public InitialOrder() {}

    public InitialOrder(List<String> items, String addressDestination, String userCode) {
        this.code = UUID.randomUUID().toString();
        this.items = items;
        this.addressDestination = addressDestination;
        this.userCode = userCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public List<String> getItems() {
        return items;
    }

    public String getAddressDestination() {
        return addressDestination;
    }

    public String getUserCode() {
        return userCode;
    }
}
