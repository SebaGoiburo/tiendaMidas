package com.tiendaMidas.tiendaMidas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendaMidas.tiendaMidas.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  Optional<Product> findByNombre(String nombre);

  Optional<Product> findById(Integer id);

  //boolean existsByNombre(String nombre);

  //boolean existById(Integer id);

  }
