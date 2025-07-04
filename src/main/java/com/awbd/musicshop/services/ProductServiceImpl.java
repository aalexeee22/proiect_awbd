package com.awbd.musicshop.services;

import com.awbd.musicshop.domain.Info;
import com.awbd.musicshop.domain.Product;
import com.awbd.musicshop.dtos.ProductDTO;
import com.awbd.musicshop.exceptions.ResourceNotFoundException;
import com.awbd.musicshop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> findAll() {
        logger.info("Retrieving all products.");
        List<Product> products = new LinkedList<>();
        productRepository.findAll(Sort.by("name")).iterator().forEachRemaining(products::add);

        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long l) {
        logger.info("Searching for product with id: {}", l);
        Optional<Product> productOptional = productRepository.findById(l);
        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException("product " + l + " not found");
        }
        return modelMapper.map(productOptional.get(), ProductDTO.class);
    }

    @Override
    public ProductDTO save(ProductDTO product) {
        logger.info("Saving product: {}", product.getName());
        Product productToSave = modelMapper.map(product, Product.class);
        productToSave.getInfo().setProduct(productToSave);
        Product savedProduct = productRepository.save(productToSave);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        logger.warn("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public void savePhotoFile(ProductDTO productDTO, MultipartFile file) {
        Product product = modelMapper.map(productDTO, Product.class);
        try {
            byte[] byteObjects = new byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            Info info = product.getInfo();
            if (info == null) {
                info = new Info();
            }

            info.setProduct(product);
            product.setInfo(info);

            if (byteObjects.length > 0) {
                info.setPhoto(byteObjects);
            }

            productRepository.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> modelMapper.map(product, ProductDTO.class));
    }
}
