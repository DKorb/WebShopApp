package com.webshopapp.customerpanel;

import com.webshopapp.common.entity.category.Category;
import com.webshopapp.customerpanel.category.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class CategoryRepositoryTests {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void testListAllEnabledCategories() {
        List<Category> allEnabledCategories = categoryRepository.findAllEnabledCategories();
        allEnabledCategories.forEach(category -> {
            log.info(category.getName() + " STATUS: {} ", category.isStatus());
        });
    }

    @Test
    void testFindCategoryByAlias() {
        String alias = "cameras";
        Category categoryByAlias = categoryRepository.findCategoryByAlias(alias);

        assertThat(categoryByAlias).isNotNull();
    }
}