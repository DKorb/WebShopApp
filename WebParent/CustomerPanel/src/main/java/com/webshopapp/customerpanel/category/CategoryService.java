package com.webshopapp.customerpanel.category;

import com.webshopapp.common.entity.category.Category;
import com.webshopapp.common.exceptions.CategoryNotFoundException;
import com.webshopapp.customerpanel.category.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {


    private CategoryRepository categoryRepository;

    public List<Category> listAllCategories() {
        return categoryRepository.findAllEnabledCategories();
    }

    public Category getCategory(String alias) throws CategoryNotFoundException {
        Category categoryByAlias = categoryRepository.findCategoryByAlias(alias);

        if (categoryByAlias == null) {
            throw new CategoryNotFoundException("Could nof find any categories with alias " + alias);
        }

        return categoryByAlias;
    }
}