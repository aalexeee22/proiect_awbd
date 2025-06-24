package com.awbd.musicshop.controllers;

import com.awbd.musicshop.dtos.ProductDTO;
import com.awbd.musicshop.services.CategoryService;
import com.awbd.musicshop.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    /**
     * Test complet pentru salvarea unui produs cu toate câmpurile necesare.
     * Folosește userul admin.
     */
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    public void testSaveOrUpdate_WithValidProduct_ShouldSaveProduct() throws Exception {
        // Pregătim datele produsului care va fi salvat
        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("Test Product");
        product.setCode("CODE123");
        product.setReservePrice(100.0);
        product.setRestored(false);

        // Simulăm salvarea în service
        when(productService.save(any())).thenReturn(product);

        // Executăm request-ul POST (multipart)
        mockMvc.perform(multipart("/products")
                        .file("imagefile", new byte[0]) // simulăm fișier gol
                        .param("id", "1")
                        .param("name", "Test Product")
                        .param("code", "CODE123")
                        .param("reservePrice", "100.0")
                        .param("restored", "false")
                        .contentType("multipart/form-data"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));

        // Verificăm dacă metoda save a fost apelată corect
        ArgumentCaptor<ProductDTO> argumentCaptor = ArgumentCaptor.forClass(ProductDTO.class);
        verify(productService, times(1)).save(argumentCaptor.capture());

        ProductDTO savedProduct = argumentCaptor.getValue();
        assertEquals("Test Product", savedProduct.getName());
        assertEquals("CODE123", savedProduct.getCode());
        assertEquals(100.0, savedProduct.getReservePrice());
    }
}
