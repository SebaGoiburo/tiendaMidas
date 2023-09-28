package com.tiendaMidas.tiendaMidas.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.tiendaMidas.tiendaMidas.enums.PaymentMethod;

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
@Table(name = "purchase")
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(nullable = false)
  private UserTienda userPurchase;

  @JdbcTypeCode(SqlTypes.JSON)
  private List<Product> purchaseProducts;

  private Double monto;

  private LocalDate date;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  public Purchase() {
  }

  public Purchase(UserTienda userPurchase, List<Product> purchaseProducts, Double monto, LocalDate date,
      PaymentMethod paymentMethod) {
    this.userPurchase = userPurchase;
    this.purchaseProducts = purchaseProducts;
    this.monto = monto;
    this.date = date;
    this.paymentMethod = paymentMethod;
  }

}
