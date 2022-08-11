package com.webshopapp.customerpanel.cart;

import com.webshopapp.common.entity.cart.Cart;
import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.product.Product;
import com.webshopapp.customerpanel.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;

    private ProductRepository productRepository;

    public Integer addProductToCart(Integer productId, Integer quantity, Customer customer) {

        Integer updatedQuantity = quantity;
        Product product = new Product(productId);

        Cart cartItem = cartRepository.findByCustomerAndProduct(customer, product);

        if (cartItem != null) {
            updatedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(updatedQuantity);
        } else {
            cartItem = new Cart();
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);
        }

        cartItem.setQuantity(updatedQuantity);

        cartRepository.save(cartItem);

        return updatedQuantity;

    }

    public List<Cart> listCartItems(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    public float updateQuantity(Integer productId, Integer quantity, Customer customer) {
        cartRepository.updateQuantity(quantity, customer.getId(), productId);
        Product product = productRepository.findById(productId).get();
        float subTotalPrice = product.getPrice() * quantity;

        return subTotalPrice;
    }

}