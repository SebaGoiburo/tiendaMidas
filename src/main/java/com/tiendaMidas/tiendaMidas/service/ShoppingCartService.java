package com.tiendaMidas.tiendaMidas.service;

import java.util.ArrayList;

import org.hibernate.mapping.List;
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

  @Autowired
  private ProductService productService;


  public void agregarProducto(Integer idProduct, Integer userId) {
    //Recupero el id del producto que quiero agregar y el del usuario.
    //Copio en un usuario local, el usuario buscado por id
    UserTienda user = userRepository.buscarPorId(userId);
    //Recupero la lista de productos que tiene su carrito de compras
    ArrayList<Product> productosDeSuCarrito = (ArrayList<Product>) user.getShoppingCart().getProductList();
    //Agrego el producto en cuestión buscandolo por id
    productosDeSuCarrito.add(productService.buscarPorId(idProduct));
    s
    //Seteo el nuevo listado de productos que tiene el cliente ahora en su carrito de compras
    
    userRepository.updateShoppingCart(userCart, userId);
  }

  public void eliminarProducto(Integer productId, Integer userId) {
    UserTienda user = userRepository.buscarPorId(userId);
    ArrayList<Product> userCart = (ArrayList<Product>) user.getUserShoppingCart();
    userCart.removeIf(uC -> uC.getId() == productId);
    userRepository.updateShoppingCart(userCart, userId);
  }


}