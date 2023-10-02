package com.tiendaMidas.tiendaMidas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiendaMidas.tiendaMidas.entities.Product;
import com.tiendaMidas.tiendaMidas.entities.UserTienda;
import com.tiendaMidas.tiendaMidas.exception.SpringException;
import com.tiendaMidas.tiendaMidas.service.ProductService;
import com.tiendaMidas.tiendaMidas.service.UserService;

@Controller
@RequestMapping("/image")
public class ImageController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/userImage/{id}")
    public ResponseEntity<byte[]> userImage(@PathVariable Integer id) {

        try {
            UserTienda u = userService.findUserById(id);
            if (u.getUserImage() == null) {
                throw new SpringException("El usuario no tiene foto");
            }
            byte[] foto = u.getUserImage().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/productImage/{id}")
    public ResponseEntity<byte[]> productImage(@PathVariable Integer id) {

        try {
            Product p = productService.buscarPorId(id);
            if (p.getProductImage() == null) {
                throw new SpringException("El producto no tiene foto");
            }
            byte[] foto = p.getProductImage().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
