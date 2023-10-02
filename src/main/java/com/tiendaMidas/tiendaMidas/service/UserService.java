package com.tiendaMidas.tiendaMidas.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.tiendaMidas.tiendaMidas.entities.Image;
import com.tiendaMidas.tiendaMidas.entities.Product;
import com.tiendaMidas.tiendaMidas.entities.UserTienda;
import com.tiendaMidas.tiendaMidas.enums.Rol;
import com.tiendaMidas.tiendaMidas.exception.SpringException;
import com.tiendaMidas.tiendaMidas.repository.ImageRepository;
import com.tiendaMidas.tiendaMidas.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private ImageService imageService;

  @Autowired
  private ImageRepository imageRepository;

  private String mensaje = "No existe ningún usuario asociado con el ID %s";

  @Transactional
  public void createUser(String email, String password, String userName, MultipartFile image) throws SpringException {
    if (userRepository.existsByEmail(email)) {
      throw new SpringException("Ya existe un usuario asociado al correo ingresado");
    }
    UserTienda userTienda = new UserTienda();
    userTienda.setEmail(email);
    userTienda.setUser_name(userName);
    userTienda.setPassword(encoder.encode(password));
    //Si el usuario no ingresa una foto, le seteamos una foto por defecto
    if (image.isEmpty()) {
      userTienda.setUserImage(imageRepository.findByNombre("user"));
    } else {
      userTienda.setUserImage(imageService.keep(image));
    }
    userTienda.setAlta(true);
    //Si se trata del primer usuario en registrarse, lo convertimos en Administrador, si no le damos el estatus de Cliente
    if (userRepository.findAll().isEmpty()) {
      userTienda.setRol(Rol.ADMIN);
    } else {
      userTienda.setRol(Rol.CUSTOMER);
    } 
    userRepository.save(userTienda);
  }

  @Transactional
  public void updateUser(UserTienda dto, MultipartFile image) throws SpringException {
    UserTienda userTienda = userRepository.findById(dto.getId())
        .orElseThrow(() -> new SpringException(String.format(mensaje, dto.getId())));
    userTienda.setUser_name(dto.getUser_name());
    userTienda.setPassword(encoder.encode(dto.getPassword()));

    Image img = new Image();
    img = imageService.keep((MultipartFile) dto.getUserImage());
    userTienda.setUserImage(img);

    userRepository.save(userTienda);
  }

  @Transactional(readOnly = true)
  public List<UserTienda> listUsers() {
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public UserTienda findUserById(Integer id) throws SpringException {
    return userRepository.findById(id).orElseThrow(() -> new SpringException(String.format(mensaje, id)));
  }


  @Transactional
  public void deleteUserById(Integer id) {
    userRepository.deleteById(id);
  }

  @Transactional
  public void deleteShoppingCart(Integer idUser){
    Optional<UserTienda> u = userRepository.findById(idUser);
    if (u.isPresent()) {
        ArrayList<Product> newShoppingCart = new ArrayList<Product>();
        u.get().setShoppingCart(newShoppingCart);
        userRepository.save(u.get());
    }
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserTienda userTienda = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("No existe un usuario asociado al correo ingresado"));
    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userTienda.getRol().name());

    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes();
    HttpSession session = attributes.getRequest().getSession(true);

    session.setAttribute("usuariosession", userTienda);

    return new User(userTienda.getEmail(), userTienda.getPassword(), Collections.singletonList(authority));
  }

}
