package com.awbd.musicshop.repositories;
import com.awbd.musicshop.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}