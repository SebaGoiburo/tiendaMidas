package com.tiendaMidas.tiendaMidas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiendaMidas.tiendaMidas.entities.Product;
import com.tiendaMidas.tiendaMidas.entities.Purchase;
import com.tiendaMidas.tiendaMidas.exception.SpringException;
import com.tiendaMidas.tiendaMidas.repository.PurchaseRepository;

@Service
public class PurchaseService {

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Autowired
  private UserService userService;

  private final String MENSAJE = "No existe ningún ticket asociado con el ID %s";

  @Transactional
  public void create(Integer idUser) throws SpringException {
    //Recupero el id del cliente y creo una compra, seteo sus atributos según el carrito de compras que tiene vigente el cliente
    //y una vez que creo la compra, borro el carrito de ese cliente porque ahora, ha pasado a ser
    // un item de "Purchases", de compras hechas por ese cliente 
    Purchase compra = new Purchase();
    compra.setUserPurchase(userService.findById(idUser));
    compra.setPurchaseProducts(userService.findById(idUser).getShoppingCart());
    ArrayList<Product> p = (ArrayList<Product>)userService.findById(idUser).getShoppingCart();
    Double monto=0.00;
    for (Product product : p) {
      monto += product.getPrecio();
    }
    compra.setMonto(monto);
    compra.setDate(LocalDate.now());
    purchaseRepository.save(compra);
    userService.deleteShoppingCart(idUser);;  
  }

  @Transactional(readOnly = true)
  public List<Purchase> buscarTodos() {
    return purchaseRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Purchase buscarPorId(Integer id) throws SpringException {
    return purchaseRepository.findById(id).orElseThrow(() -> new SpringException(String.format(MENSAJE, id)));
  }

}
