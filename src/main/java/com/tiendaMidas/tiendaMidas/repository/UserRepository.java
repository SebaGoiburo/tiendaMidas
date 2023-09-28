package com.tiendaMidas.tiendaMidas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tiendaMidas.tiendaMidas.entities.Product;
import com.tiendaMidas.tiendaMidas.entities.UserTienda;

@Repository
public interface UserRepository extends JpaRepository<UserTienda, Integer> {

  Optional<UserTienda> findByEmail(String emailString);

  UserTienda buscarPorId(Integer id);

  boolean existsByEmail(String emailString);

  @Modifying
  @Query("UPDATE User u SET u.alta = true WHERE u.id = :id")
  void enable(@Param("id") Integer id);

  @Modifying
  @Query("UPDATE User u SET u.userShoppingCart = :cart WHERE u.id = :id")
  void updateShoppingCart(@Param("cart") List<Product> cart, @Param("id") Integer id);

}
