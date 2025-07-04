package com.awbd.musicshop.mappers;

import com.awbd.musicshop.domain.Category;
import com.awbd.musicshop.dtos.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDTO toDto(Category category) {
        Long id = category.getId();
        String name= category.getName();
        return new CategoryDTO(id, name);
    }

    public Category toCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
}