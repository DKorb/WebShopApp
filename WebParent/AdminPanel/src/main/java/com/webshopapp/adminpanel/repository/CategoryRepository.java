package com.webshopapp.adminpanel.repository;


import com.webshopapp.common.entity.category.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    Long countById(Integer id);

    @Query("UPDATE Category categories SET categories.status = ?2 WHERE categories.id = ?1")
    @Modifying
    void updateStatus(Integer id, boolean status);
}