package com.tiendaMidas.tiendaMidas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tiendaMidas.tiendaMidas.entities.Purchase;
import com.tiendaMidas.tiendaMidas.exception.SpringException;
import com.tiendaMidas.tiendaMidas.service.PurchaseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/purchase")
@SecurityRequirement(name = "bearerAuth")
public class PurchaseController {
    
    @Autowired
    private PurchaseService purchaseService;
    
    @PostMapping("/buy/{id}")
    public void buy(@PathVariable Integer id){
        try {
            purchaseService.createPurchase(id);
        } catch (SpringException e) {
        }
    }

    @GetMapping("/listPurchases")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public List<Purchase> listPurchases(){
        return purchaseService.buscarTodos();
    }
    

}
