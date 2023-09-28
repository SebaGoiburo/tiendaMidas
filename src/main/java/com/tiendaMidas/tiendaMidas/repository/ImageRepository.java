package com.tiendaMidas.tiendaMidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendaMidas.tiendaMidas.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

  Image findByNombre(String nombre);

}
