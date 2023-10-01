package com.tiendaMidas.tiendaMidas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tiendaMidas.tiendaMidas.entities.Product;
import com.tiendaMidas.tiendaMidas.entities.UserTienda;
import com.tiendaMidas.tiendaMidas.exception.SpringException;
import com.tiendaMidas.tiendaMidas.repository.ProductRepository;
import com.tiendaMidas.tiendaMidas.service.ProductService;
import com.tiendaMidas.tiendaMidas.service.UserService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/listProducts")
    @ResponseBody
    public List<Product> listProducts(){
        List<Product> list= productService.buscarTodos();
        return list;
    }

    @PostMapping("/createProduct")
    public void createProduct(@RequestParam String nombre, Double precio, Integer stock, MultipartFile imageProduct ){
        try {
            productService.createProduct(nombre, precio, stock, imageProduct);
        } catch (SpringException e) {
        }
    }
    
    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable Integer id, String nombre, Double precio, Integer stock, MultipartFile image){
        try {
            productService.modificar(id, nombre, precio, stock, image);
        } catch (SpringException e) {
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Integer id){
        if(productRepository.findById(id).isPresent()){
                productService.eliminar(id);
        }
    }
    

    @PostMapping("{idProduct}/addToCart/{idUser}")
    public void addToCart(@PathVariable Integer idProduct, Integer idUser){
        try {
        Product p = productService.buscarPorId(idProduct);
        UserTienda u = userService.findById(idUser);
        ArrayList<Product> l = (ArrayList<Product>) u.getShoppingCart();
        l.add(p);
        u.setShoppingCart(l);
        } catch (SpringException e) {
        }
    }

    

}
