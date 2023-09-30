package com.tiendaMidas.tiendaMidas.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tiendaMidas.tiendaMidas.entities.UserTienda;
import com.tiendaMidas.tiendaMidas.exception.SpringException;
import com.tiendaMidas.tiendaMidas.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired 
    private UserService userService;

    
    @PostMapping("/singin")
    public void signin(@RequestParam String email, String password, String userName, MultipartFile image,  HttpServletRequest request){
        try {
            userService.create(email, password, userName, image);
            request.login(email, password);
        } catch (SpringException e) {
        } catch (ServletException e) {
        }
    }    

    @GetMapping("/listUsers")
    @ResponseBody
    public List<UserTienda> listUsers(){
        List<UserTienda> list= userService.findAll();
        return list;
    }

    

    
}
