package com.webshopapp.customerpanel.cart;

import com.webshopapp.common.entity.cart.Cart;
import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByCustomer(Customer customer);

    Cart findByCustomerAndProduct(Customer customer, Product product);

    @Modifying
    @Query("UPDATE Cart c SET c.quantity = ?1 WHERE c.customer.id = ?2 AND c.product.id = ?3")
    void updateQuantity(Integer quantity, Integer customerId, Integer productId);

}