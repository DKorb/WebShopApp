package com.webshopapp.customerpanel.service;

import com.webshopapp.common.entity.product.Product;
import com.webshopapp.common.exceptions.ProductNotFoundException;
import com.webshopapp.customerpanel.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    public static final int PRODUCTS_PER_PAGE = 10;
    public static final int SEARCH_RESULT_PER_PAGE = 10;

    ProductRepository productRepository;

    public Page<Product> listByCategory(int pageNumber, Integer categoryID) {
        Pageable page = PageRequest.of(pageNumber - 1, PRODUCTS_PER_PAGE);
        return productRepository.getAllProductByCategory(categoryID, page);
    }

    public Product getProduct(String alias) throws ProductNotFoundException {
        Product productByAlias = productRepository.getProductByAlias(alias);

        if (productByAlias == null) {
            throw new ProductNotFoundException("Could not find any product with alias" + alias);
        }

        return productByAlias;
    }

    public Page<Product> search(String keyword, int pageNumber) {
        var page = PageRequest.of(pageNumber - 1, SEARCH_RESULT_PER_PAGE);
        return productRepository.searchProductByNameAndFullDescription(keyword, page);
    }
}