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

  boolean existsByEmail(String emailString);


}
