package com.webshopapp.customerpanel.repository;

import com.webshopapp.common.entity.category.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.status = true ORDER BY c.name ASC")
    public List<Category> findAllEnabledCategories();
}