package com.tiendaMidas.tiendaMidas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiendaMidas.tiendaMidas.entities.UserTienda;
import com.tiendaMidas.tiendaMidas.enums.Rol;
import com.tiendaMidas.tiendaMidas.jwt.AuthResponse;
import com.tiendaMidas.tiendaMidas.jwt.LoginRequest;
import com.tiendaMidas.tiendaMidas.jwt.RegisterRequest;
import com.tiendaMidas.tiendaMidas.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request){
        UserTienda user = UserTienda.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(request.getPassword())
            // .ShoppingCart(request.getShoppingCart())
            // .purchases(request.getPurchases())
            // .userImage(request.getUserImage())
            .rol(Rol.CUSTOMER)
            .alta(true)
            .build();
            
        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }

}
