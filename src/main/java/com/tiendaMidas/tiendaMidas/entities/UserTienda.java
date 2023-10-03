package com.tiendaMidas.tiendaMidas.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


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
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "user")
public class UserTienda implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToMany
  @Column(nullable = true)
  private List<Product> ShoppingCart;

  @OneToMany
  @Column(nullable = true)
  private List<Purchase> purchases;

  @OneToOne
  @PrimaryKeyJoinColumn
  private Image userImage;

  @Enumerated(EnumType.STRING)
  private Rol rol;

  private Boolean alta;

  public UserTienda() {
  }


  public UserTienda(String username, String email, String password, Image userImage) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.userImage = userImage;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
   return List.of(new SimpleGrantedAuthority(rol.name()));
  }


  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }


  @Override
  public boolean isAccountNonLocked() {
    return true;
  }


  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }


  @Override
  public boolean isEnabled() {
    return true;
  }


  

}
