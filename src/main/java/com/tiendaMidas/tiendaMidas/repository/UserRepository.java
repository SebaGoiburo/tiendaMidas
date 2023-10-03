package com.tiendaMidas.tiendaMidas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendaMidas.tiendaMidas.entities.UserTienda;


@Repository
public interface UserRepository extends JpaRepository<UserTienda, Integer> {

  Optional<UserTienda> findByEmail(String emailString);

  Optional<UserTienda> findByUsername(String username);

  boolean existsByEmail(String emailString);

  Optional<UserTienda> findById(Integer id);

  
}
