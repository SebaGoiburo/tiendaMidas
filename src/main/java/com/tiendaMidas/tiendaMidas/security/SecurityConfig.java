package com.tiendaMidas.tiendaMidas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/home" ,"/login", "/registro", "/usuario/crear" , "/css/*", "/assets/*", "/img/*", "/templates/fragments/*", "/foto/*").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                                .loginPage("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/home", true)
                                .failureUrl( "/login?error=true")
                        )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/home")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID"))
                .csrf()
                .disable()
                ;

        return http.build();
    }

}