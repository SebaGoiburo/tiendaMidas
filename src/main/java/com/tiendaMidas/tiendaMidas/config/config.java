package com.tiendaMidas.tiendaMidas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class config {

  @Autowired
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

}
