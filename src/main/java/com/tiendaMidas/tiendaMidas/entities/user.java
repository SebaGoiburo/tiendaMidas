package com.tiendaMidas.tiendaMidas.entities;

import java.util.ArrayList;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String user_name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @JdbcTypeCode(SqlTypes.JSON)
  private List<Purchase> purchases;

  @OneToOne
  private Image userImage;

  @Enumerated(EnumType.STRING)
  private Rol rol;

  private Boolean alta;

  public User() {
  }

  public User(String user_name, String email, String password, Image userImage) {
    this.user_name = user_name;
    this.email = email;
    this.password = password;
    this.userImage = userImage;
  }

}
