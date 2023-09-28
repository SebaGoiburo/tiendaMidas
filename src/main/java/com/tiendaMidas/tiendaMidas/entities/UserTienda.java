package com.tiendaMidas.tiendaMidas.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.tiendaMidas.tiendaMidas.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
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

  @Column
  private List<Product> userShoppingCart;

  @JdbcTypeCode(SqlTypes.JSON)
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

  public Integer getId() {
    return id;
  }

  public String getUser_name() {
    return user_name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public List<Product> getUserShoppingCart() {
    return userShoppingCart;
  }

  public List<Purchase> getPurchases() {
    return purchases;
  }

  public Image getUserImage() {
    return userImage;
  }

  public Rol getRol() {
    return rol;
  }

  public Boolean getAlta() {
    return alta;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUserShoppingCart(List<Product> userShoppingCart) {
    this.userShoppingCart = userShoppingCart;
  }

  public void setPurchases(List<Purchase> purchases) {
    this.purchases = purchases;
  }

  public void setUserImage(Image userImage) {
    this.userImage = userImage;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public void setAlta(Boolean alta) {
    this.alta = alta;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", user_name=" + user_name + ", email=" + email + ", password=" + password
        + ", purchases=" + purchases + ", userImage=" + userImage + ", rol=" + rol + ", alta=" + alta + "]";
  }

}
