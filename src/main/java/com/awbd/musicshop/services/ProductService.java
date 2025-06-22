package com.awbd.musicshop.services;

import com.awbd.musicshop.dtos.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long l);
    ProductDTO save(ProductDTO product);
    void deleteById(Long id);

    void savePhotoFile(ProductDTO product, MultipartFile file);
}
