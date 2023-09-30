package com.tiendaMidas.tiendaMidas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tiendaMidas.tiendaMidas.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  Optional<Product> findByNombre(String nombre);

  Optional<Product> findById(Integer id);

  boolean existsByNombre(String nombre);

  boolean existById(Integer id);

  @Modifying
  @Query("UPDATE Producto p SET p.alta = true WHERE p.id = :id")
  void enable(@Param("id") Integer id);

}
