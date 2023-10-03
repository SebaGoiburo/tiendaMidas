package com.tiendaMidas.tiendaMidas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendaMidas.tiendaMidas.jwt.AuthResponse;
import com.tiendaMidas.tiendaMidas.jwt.LoginRequest;
import com.tiendaMidas.tiendaMidas.jwt.RegisterRequest;
import com.tiendaMidas.tiendaMidas.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));

    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
    

}
