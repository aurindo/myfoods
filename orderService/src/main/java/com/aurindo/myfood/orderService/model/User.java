package com.aurindo.myfood.orderService.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @NotNull
    @Column(unique = true)
    protected String code;

    @NotNull
    protected String name;

    @NotNull
    @Column(unique = true)
    protected String username;

    @NotNull
    protected String password;

    @NotNull
    protected String email;

    @NotNull
    protected Boolean enable;

    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<String> roles;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getEnable() {
        return enable;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
