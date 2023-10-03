package com.tiendaMidas.tiendaMidas.jwt;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tiendaMidas.tiendaMidas.entities.Image;
import com.tiendaMidas.tiendaMidas.entities.Product;
import com.tiendaMidas.tiendaMidas.entities.Purchase;
import com.tiendaMidas.tiendaMidas.enums.Rol;

import lombok.AllArgsConstructor;
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
