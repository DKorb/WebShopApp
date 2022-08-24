package com.webshopapp.customerpanel.cart;

import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.exceptions.CustomerNotFoundException;
import com.webshopapp.customerpanel.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class CartRestController {

    private CartService cartService;

    private CustomerService customerService;

    @PostMapping("/cart/add/{productId}/{quantity}")
    public String addProductToCart(@PathVariable(name = "productId") Integer productId,
                                   @PathVariable(name = "quantity") Integer quantity,
                                   HttpServletRequest request) {
        try {
            Customer customer = getCustomer(request);
            Integer updatedQuantity = cartService.addProductToCart(productId, quantity, customer);

            return updatedQuantity + " item(s) of this product were added to your cart.";
        } catch (CustomerNotFoundException ex) {
            return "You must login to add product to cart.";
        }
    }

    private Customer getCustomer(HttpServletRequest request) throws CustomerNotFoundException {
        String customerEmail = customerService.getEmailFromCustomer(request);

        if (customerEmail == null) {
            throw new CustomerNotFoundException("No authenticated customer");
        }

        return customerService.getCustomerByEmail(customerEmail);
    }

    @PostMapping("/cart/update/{productId}/{quantity}")
    public String updateQuantity(@PathVariable(name = "productId") Integer productId,
                                   @PathVariable(name = "quantity") Integer quantity,
                                   HttpServletRequest request) {
        try {
            Customer customer = getCustomer(request);
            float subTotalPrice = cartService.updateQuantity(productId, quantity, customer);

            return String.valueOf(subTotalPrice);
        } catch (CustomerNotFoundException ex) {
            return "You must login to change quantity of product.";
        }
    }

    @DeleteMapping("/cart/remove/{productId}")
    public String removeProduct(@PathVariable("productId") Integer productId,
                                HttpServletRequest request) {
        try {
            Customer customer = getCustomer(request);
            cartService.removeProduct(customer, productId);

            return "The product has been removed from your shopping cart.";

        } catch (CustomerNotFoundException e) {
            return "You must login to remove product.";
        }
    }
}