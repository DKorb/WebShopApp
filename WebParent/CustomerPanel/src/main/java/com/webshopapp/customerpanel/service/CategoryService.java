package com.webshopapp.customerpanel.service;

import com.webshopapp.common.entity.category.Category;
import com.webshopapp.customerpanel.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAllCategories() {
        List<Category> allEnabledCategories = categoryRepository.findAllEnabledCategories();
        return allEnabledCategories;
    }
}