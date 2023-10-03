package com.tiendaMidas.tiendaMidas.jwt;

import org.springframework.stereotype.Component;

import com.tiendaMidas.tiendaMidas.enums.Rol;

import lombok.Data;


@Data
@Component
public class RegisterRequest {
   
    String username;
    String email;
    String password;
    // List<Product> ShoppingCart;
    // List<Purchase> purchases;
    // Image userImage;
    Rol rol;
    Boolean alta;
}
