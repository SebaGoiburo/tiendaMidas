package com.tiendaMidas.tiendaMidas.entities;

import java.util.ArrayList;

import org.hibernate.mapping.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Carts")
public class ShoppingCart {
  
    Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User userShoppingCart;

    private ArrayList<Product> purchaseList;

    private double precioTotal;

    @Enumerated(EnumType.STRING)
    private ShoppingCartState estado;

	public ShoppingCart() {
	}

	public ShoppingCart(User userShoppingCart, ArrayList<Product> purchaseList, double precioTotal,
			ShoppingCartState estado) {
		this.userShoppingCart = userShoppingCart;
		this.purchaseList = purchaseList;
		this.precioTotal = precioTotal;
		this.estado = estado;
	}


}
