package com.tiendaMidas.tiendaMidas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private double precio;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Image productImage;

    private int stock;

    private boolean alta;

    public Product() {
    }

    public Product(String categoria, String nombre, double precio, int stock, Image productImage) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.productImage = productImage;
    }

}
