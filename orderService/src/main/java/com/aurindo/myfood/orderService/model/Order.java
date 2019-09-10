package com.aurindo.myfood.orderService.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="customer_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String code;

    @NotNull
    @NotEmpty
    @ManyToMany
    private List<Product> items;

    @NotNull
    private String addressDestination;

    @NotNull
    private String userCode;

    @NotNull
    private OrderStatus status;

    public Order() {}

    public Order(
            @NotNull String code,
            @NotNull List<Product> items,
            @NotNull String addressDestination,
            @NotNull String userCode) {
        this.code = code;
        this.items = items;
        this.addressDestination = addressDestination;
        this.userCode = userCode;
        this.status = OrderStatus.NEW;
    }

    public Order(
            @NotNull String code,
            @NotNull List<Product> items,
            @NotNull String addressDestination,
            @NotNull String userCode,
            @NotNull OrderStatus status) {
        this.code = code;
        this.items = items;
        this.addressDestination = addressDestination;
        this.userCode = userCode;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public List<Product> getItems() {
        return items;
    }

    public String getAddressDestination() {
        return addressDestination;
    }

    public String getUserCode() {
        return userCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
