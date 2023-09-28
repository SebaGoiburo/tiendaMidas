package com.tiendaMidas.tiendaMidas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tiendaMidas.tiendaMidas.entities.Image;
import com.tiendaMidas.tiendaMidas.exception.SpringException;
import com.tiendaMidas.tiendaMidas.repository.ImageRepository;

@Service
public class ImageService {

  @Autowired
  private ImageRepository imageRepository;

  public Image keep(MultipartFile img) throws SpringException {
    if (img != null) {
      try {
        Image pic = new Image();
        pic.setMime(img.getContentType());
        pic.setNombre(img.getName());
        pic.setContenido(img.getBytes());
        return imageRepository.save(pic);
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    return null;
  }

  public Image update(Integer id, MultipartFile img) throws SpringException {
    if (img != null) {
      try {
        Image pic = new Image();

        if (id != null) {
          Optional<Image> respuesta = imageRepository.findById(id);
          if (respuesta.isPresent()) {
            pic = respuesta.get();
          }
        }

        pic.setMime(img.getContentType());
        pic.setNombre(img.getName());
        pic.setContenido(img.getBytes());
        return imageRepository.save(pic);
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    return null;
  }

}
