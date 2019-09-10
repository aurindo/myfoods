package com.aurindo.myfood.orderService.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="courier")
public class Courier extends User implements Serializable {

    public Courier() {
        this.code = UUID.randomUUID().toString();
    }

    public Courier(
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
