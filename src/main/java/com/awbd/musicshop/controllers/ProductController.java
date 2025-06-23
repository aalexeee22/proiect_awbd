package com.awbd.musicshop.controllers;

import com.awbd.musicshop.dtos.CategoryDTO;
import com.awbd.musicshop.dtos.ProductDTO;
import com.awbd.musicshop.services.CategoryService;
import com.awbd.musicshop.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/form")
    public String productForm(Model model) {
        ProductDTO product = new ProductDTO();
        model.addAttribute("product", product);
        List<CategoryDTO> categoriesAll = categoryService.findAll();
        model.addAttribute("categoriesAll", categoriesAll);
        return "productForm";
    }

    @GetMapping("")
    public String productList(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "id") String sortBy,
                              Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<ProductDTO> productPage = productService.findAllPaged(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);

        return "productList";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        try {
            Long productId = Long.valueOf(id);
            ProductDTO productDTO = productService.findById(productId);
            model.addAttribute("product", productDTO);
        } catch (NumberFormatException | com.awbd.musicshop.exceptions.ResourceNotFoundException e) {
            model.addAttribute("exception", e);
            return "notFoundException";
        }

        List<CategoryDTO> categoriesAll = categoryService.findAll();
        model.addAttribute("categoriesAll", categoriesAll);

        return "productForm";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        productService.deleteById(Long.valueOf(id));
        return "redirect:/products";
    }

    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute ProductDTO product,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            List<CategoryDTO> categoriesAll = categoryService.findAll();
            model.addAttribute("categoriesAll", categoriesAll);
            return "productForm";
        }

        productService.save(product);
        return "redirect:/products";
    }
}
