package com.webshopapp.customerpanel.service;

import com.webshopapp.common.entity.product.Product;
import com.webshopapp.common.exceptions.ProductNotFoundException;
import com.webshopapp.customerpanel.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public static final int PRODUCTS_PER_PAGE = 10;

    @Autowired
    ProductRepository productRepository;

    public Page<Product> listByCategory(int pageNumber, Integer categoryID) {
        Pageable pageable = PageRequest.of(pageNumber - 1, PRODUCTS_PER_PAGE);
        return productRepository.getAllProductByCategory(categoryID, pageable);
    }
}