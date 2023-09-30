package com.tiendaMidas.tiendaMidas.entities;

import java.util.ArrayList;

import org.hibernate.mapping.List;

import com.tiendaMidas.tiendaMidas.enums.ShoppingCartState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserTienda userShoppingCart;

    private ArrayList<Product> productList;

    private double precioTotal;

    public ShoppingCart() {
    }

    public ShoppingCart(UserTienda userShoppingCart, ArrayList<Product> productList, double precioTotal) {
        this.userShoppingCart = userShoppingCart;
        this.productList = productList;
        this.precioTotal = precioTotal;
    }

}
