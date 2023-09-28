package com.tiendaMidas.tiendaMidas.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaMidas.tiendaMidas.entities.Product;
import com.tiendaMidas.tiendaMidas.entities.ShoppingCart;
import com.tiendaMidas.tiendaMidas.entities.UserTienda;
import com.tiendaMidas.tiendaMidas.repository.UserRepository;

@Service
public class ShoppingCartService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  public void agregarProducto(Product producto, Integer userId) {

    UserTienda user = userRepository.buscarPorId(userId);
    ArrayList<Product> userCart = (ArrayList<Product>) user.getUserShoppingCart();
    userCart.add(producto);
    userRepository.updateShoppingCart(userCart, userId);
  }

  public void eliminarProducto(Integer productId, Integer userId) {

    UserTienda user = userRepository.buscarPorId(userId);
    ArrayList<Product> userCart = (ArrayList<Product>) user.getUserShoppingCart();
    userCart.removeIf(uC -> uC.getId() == productId);
    userRepository.updateShoppingCart(userCart, userId);
  }

}