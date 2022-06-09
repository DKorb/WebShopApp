package com.webshopapp.adminpanel;


import com.webshopapp.common.entity.brand.Brand;
import com.webshopapp.common.entity.category.Category;
import com.webshopapp.common.entity.product.Product;
import com.webshopapp.adminpanel.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void testCreateProduct() {
        Brand brand = testEntityManager.find(Brand.class, 7);
        Category category = testEntityManager.find(Category.class, 2);

        Product product = new Product();
        product.setName("Sony Alpha 7 IV");
        product.setAlias("sony alpha 7 iv");
        product.setFullDescription("test description");

        product.setBrand(brand);
        product.setCategory(category);
        product.setPrice(2698);
        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());
        product.setImage("main_image.png");

        Product testProduct = productRepository.save(product);

        assertThat(testProduct).isNotNull();
        assertThat(testProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllProducts() {
        Iterable<Product> allProducts = productRepository.findAll();
        allProducts.forEach(System.out::println);
    }

    @Test
    public void testGetProduct() {
        Integer productID = 1;
        Product foundProduct = productRepository.findById(productID).get();
        System.out.println(foundProduct);
        assertThat(foundProduct).isNotNull();
    }

    @Test
    public void testSaveProductWithDetails() {
        Product product = productRepository.findById(1).get();

        product.addDetails("SIM card", "Dual SIM");
        product.addDetails("Memory storage", "128 GB");
        product.addDetails("Battery power", "16");
        product.addDetails("Cellular Technology", "LTE, UMTS");

        Product newProduct = productRepository.save(product);
        assertThat(newProduct.getDetails()).isNotEmpty();
    }
}