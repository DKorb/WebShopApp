package com.webshopapp.customerpanel.cart;

import com.webshopapp.common.entity.cart.Cart;
import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByCustomer(Customer customer);

    Cart findByCustomerAndProduct(Customer customer, Product product);

}