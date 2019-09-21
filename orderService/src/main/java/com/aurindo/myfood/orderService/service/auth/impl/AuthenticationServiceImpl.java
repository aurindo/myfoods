package com.aurindo.myfood.orderService.service.auth.impl;

import com.aurindo.myfood.orderService.service.auth.AuthenticateService;
import com.aurindo.myfood.orderService.util.JwtTokenOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticateService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("jwtUserDetailService")
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private JwtTokenOperation jwtTokenOperation;

    @Override
    public String authenticate(String userName, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = userDetailService
                .loadUserByUsername(userName);

        return jwtTokenOperation.generateToken(userDetails);
    }

}
