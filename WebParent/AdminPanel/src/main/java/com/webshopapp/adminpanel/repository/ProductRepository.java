package com.webshopapp.adminpanel.repository;


import com.webshopapp.common.entity.product.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    public Long countById(Integer id);

    @Query("UPDATE Product product SET product.status = ?2 WHERE product.id = ?1")
    @Modifying
    public void updateStatus(Integer id, boolean status);
}