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
                .csrf()
                .disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                            "/api/v1/auth/**",
                            "/v2/api-docs",
                            "/v3/api-docs",
                            "v3/api-docs/**",
                            "/swagger-resources",
                            "/swagger-resources/**",
                            "/configuration/ui",
                            "/configuration/security",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/context-path/swagger-ui.html",
                            "/signin",
                            "/login").permitAll()
                        .anyRequest().authenticated());
                // .formLogin(form -> form
                //                 .loginPage("/login")
                //                 .usernameParameter("email")
                //                 .passwordParameter("password")
                //         )
                // .logout(logout -> logout
                //     .logoutUrl("/logout")
                //     .invalidateHttpSession(true)
                //     .deleteCookies("JSESSIONID"))
                // ;

        return http.build();
    }

}