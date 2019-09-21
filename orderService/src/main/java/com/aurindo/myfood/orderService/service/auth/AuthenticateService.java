package com.aurindo.myfood.orderService.service.auth;

public interface AuthenticateService {
    String authenticate(String userName, String password) throws Exception;
}
