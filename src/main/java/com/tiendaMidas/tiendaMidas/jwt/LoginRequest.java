package com.tiendaMidas.tiendaMidas.jwt;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginRequest {

    String username;
    String password;
    
}
