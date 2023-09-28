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
  public void crear(Product dto, MultipartFile img) throws SpringException {
    if (productRepository.existsByNombre(dto.getNombre())) {
      throw new SpringException("Ya existe un producto registrado con ese nombre");
    }
    Product producto = new Product();
    producto.setNombre(dto.getNombre());
    producto.setPrecio(dto.getPrecio());
    producto.setStock(dto.getStock());
    if (!img.isEmpty()) {
      producto.setProductImage(imageService.keep(img));
    }
    producto.setAlta(true);
    productRepository.save(producto);
  }

  @Transactional
  public void modificar(Product dto, MultipartFile img) throws SpringException {
    Product producto = productRepository.findById(dto.getId())
        .orElseThrow(() -> new SpringException(String.format(mensaje, dto.getId())));
    producto.setNombre(dto.getNombre());
    producto.setPrecio(dto.getPrecio());
    producto.setStock(dto.getStock());
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
  public void enable(Integer id) {
    productRepository.enable(id);
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
