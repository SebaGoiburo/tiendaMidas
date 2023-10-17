package com.tiendaMidas.tiendaMidas.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tiendaMidas.tiendaMidas.entities.UserTienda;
import com.tiendaMidas.tiendaMidas.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired 
    private UserService userService;  

    @GetMapping("/listUsers")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public List<UserTienda> listUsers(){
        List<UserTienda> list= userService.listUsers();
        return list;
    }

    @GetMapping("/deleteShoppingCart/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteShoppingCart(@PathVariable Integer id){
        userService.deleteShoppingCart(id);
    }    

    
}
