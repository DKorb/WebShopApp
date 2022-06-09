package com.webshopapp.customerpanel;

import com.webshopapp.common.entity.category.Category;
import com.webshopapp.customerpanel.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testListAllEnabledCategories() {
        List<Category> allEnabledCategories = categoryRepository.findAllEnabledCategories();
        allEnabledCategories.forEach(category -> {
            System.out.println(category.getName() + " STATUS: " + category.isStatus());
        });
    }
}