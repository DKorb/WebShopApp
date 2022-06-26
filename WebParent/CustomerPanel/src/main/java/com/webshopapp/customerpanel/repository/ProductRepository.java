package com.webshopapp.customerpanel.repository;

import com.webshopapp.common.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.category.id = ?1 AND p.status = true ORDER BY p.name ASC")
    Page<Product> getAllProductByCategory(Integer categoryID, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.alias = ?1")
    Product getProductByAlias(String alias);

    @Query(value = "SELECT * FROM products WHERE status = true AND " +
            "MATCH(name, full_description) AGAINST (?1)", nativeQuery = true)
    Page<Product> searchProductByNameAndFullDescription(String keyword, Pageable pageable);

}