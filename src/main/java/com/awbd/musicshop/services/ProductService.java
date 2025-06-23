package com.awbd.musicshop.services;

import com.awbd.musicshop.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO save(ProductDTO product);
    void deleteById(Long id);
    void savePhotoFile(ProductDTO product, MultipartFile file);

    Page<ProductDTO> findAllPaged(Pageable pageable);
}
