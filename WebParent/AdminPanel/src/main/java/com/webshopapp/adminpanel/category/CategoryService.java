package com.webshopapp.adminpanel.category;


import com.webshopapp.common.entity.category.Category;
import com.webshopapp.common.exceptions.CategoryNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {

    public static final int CATEGORY_PER_PAGE = 5;

    private CategoryRepository categoryRepository;


    public List<Category> listAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }


    public Category getCategory(Integer id) throws CategoryNotFoundException {
        try {
            return categoryRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new CategoryNotFoundException("Could not find aby category with ID " + id);
        }
    }

    public void deleteCategory(Integer id) throws CategoryNotFoundException {
        Long countById = categoryRepository.countById(id);

        if (countById == null || countById == 0) {
            throw new CategoryNotFoundException("Could not find aby category with ID " + id);
        }

        categoryRepository.deleteById(id);
    }

    public void updateCategoryStatus(Integer id, boolean status) {
        categoryRepository.updateStatus(id, status);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Page<Category> listByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, CATEGORY_PER_PAGE);
        return categoryRepository.findAll(pageable);
    }
}
