package com.tiendaMidas.tiendaMidas.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "purchase")
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(nullable = false)
  private UserTienda userPurchase;

  @OneToMany
  private List<Product> purchaseProducts;

  private Double monto;

  private LocalDate date;

  public Purchase() {
  }

  public Purchase(UserTienda userPurchase, List<Product> purchaseProducts, Double monto, LocalDate date) {
    this.userPurchase = userPurchase;
    this.purchaseProducts = purchaseProducts;
    this.monto = monto;
    this.date = date;
  }

}
