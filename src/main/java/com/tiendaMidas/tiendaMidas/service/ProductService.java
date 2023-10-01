package com.tiendaMidas.tiendaMidas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tiendaMidas.tiendaMidas.entities.Product;
import com.tiendaMidas.tiendaMidas.exception.SpringException;
import com.tiendaMidas.tiendaMidas.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ImageService imageService;

  private String mensaje = "No existe ningún producto asociado con el ID %s";

  @Transactional
  public void createProduct(String nombre, Double precio, Integer stock, MultipartFile img) throws SpringException {
    if (productRepository.findByNombre(nombre).isPresent()) {
      throw new SpringException("Ya existe un producto registrado con ese nombre");
    }
    Product producto = new Product();
    producto.setNombre(nombre);
    producto.setPrecio(precio);
    producto.setStock(stock);
    //Se valida si el usuario ingresa una imagen antes de setearla en el atributo del objeto producto
    if (!img.isEmpty()) {
      producto.setProductImage(imageService.keep(img));
    }
    producto.setAlta(true);
    productRepository.save(producto);
  }

  @Transactional
  public void modificar(Integer id, String nombre, Double precio, Integer stock, MultipartFile img) throws SpringException {
    Product producto = productRepository.findById(id)
        .orElseThrow(() -> new SpringException(String.format(mensaje, id)));
    producto.setNombre(nombre);
    producto.setPrecio(precio);
    producto.setStock(stock);
    //Se valida si el usuario ingresa una imagen antes de setearla en el atributo del objeto producto
    if (!img.isEmpty()) {
      producto.setProductImage(imageService.keep(img));
    }
    productRepository.save(producto);
  }

  @Transactional
  public List<Product> buscarTodos() {
    return productRepository.findAll();
  }

  
  @Transactional
  public void eliminar(Integer id) {
    productRepository.deleteById(id);
  }

  @Transactional
  public Product buscarPorId(Integer id) throws SpringException {
    return productRepository.findById(id).orElseThrow(() -> new SpringException(String.format(mensaje, id)));
  }

}
