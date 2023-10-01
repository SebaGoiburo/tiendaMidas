package com.tiendaMidas.tiendaMidas.entities;

import java.util.List;

import com.tiendaMidas.tiendaMidas.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserTienda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String user_name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToMany
  private List<Product> ShoppingCart;

  @OneToMany
  private List<Purchase> purchases;

  @OneToOne
  private Image userImage;

  @Enumerated(EnumType.STRING)
  private Rol rol;

  private Boolean alta;

  public UserTienda() {
  }


  public UserTienda(String user_name, String email, String password, Image userImage) {
    this.user_name = user_name;
    this.email = email;
    this.password = password;
    this.userImage = userImage;
  }

}
