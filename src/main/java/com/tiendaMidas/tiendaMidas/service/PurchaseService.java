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

  private final String MENSAJE = "No existe ningún ticket asociado con el ID %s";

  @Transactional
  public void create(Purchase dto) throws SpringException {

    Purchase compra = new Purchase();
    compra.setUserPurchase(dto.getUserPurchase());
    compra.setPurchaseProducts((ArrayList<Product>) dto.getPurchaseProducts());
    compra.setMonto(dto.getMonto());
    compra.setDate(LocalDate.now());
    compra.setPaymentMethod(dto.getPaymentMethod());
    purchaseRepository.save(compra);
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
