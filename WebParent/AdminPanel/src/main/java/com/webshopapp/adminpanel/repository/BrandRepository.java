package com.webshopapp.adminpanel.repository;


import com.webshopapp.common.entity.brand.Brand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {
    public Long countById(Integer id);

    @Query("SELECT NEW Brand(b.id, b.name) FROM Brand b ORDER BY b.name ASC")
    public List<Brand> findAll();
}