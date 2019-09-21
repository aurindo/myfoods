package com.aurindo.myfood.orderService.resource;

import com.aurindo.myfood.orderService.model.jwt.JwtRequest;
import com.aurindo.myfood.orderService.service.auth.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin
public class JwtAuthenticationResource {

    @Autowired
    private AuthenticateService authenticateService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody final JwtRequest jwtRequest) throws Exception {
//        String passwordEncrypted = new BCryptPasswordEncoder().encode(jwtRequest.getPassword()); //TODO: Remove it
        String jwtToken = authenticateService.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

}
