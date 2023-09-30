package com.tiendaMidas.tiendaMidas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tiendaMidas.tiendaMidas.service.UserService;

@Configuration
public class AuthConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(encoder)
                .and()
                .inMemoryAuthentication()
                .withUser("user").password("{noop}pass").roles("USER")
                .and()
                .withUser("admin").password("{noop}pass").roles("ADMIN");
    }
}
