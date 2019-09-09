package com.aurindo.myfood.orderService.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="customer")
public class Customer extends User implements Serializable {

    public Customer() {
        this.code = UUID.randomUUID().toString();
    }

    public Customer(
            @NotNull String name,
            @NotNull String username,
            @NotNull String password,
            @NotNull String email) {
        this.code = UUID.randomUUID().toString();
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
