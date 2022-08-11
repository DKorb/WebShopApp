package com.webshopapp.customerpanel.cart;

import com.webshopapp.common.entity.cart.Cart;
import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.customerpanel.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {

    private CartService cartService;

    private CustomerService customerService;

    @GetMapping("/cart")
    public String viewShoppingCart(Model model, HttpServletRequest request){
        Customer customer = getCustomer(request);
        List<Cart> cartItems = cartService.listCartItems(customer);

        float shippingPrice = 20;

        float totalPrice = 0.0F;
        float subTotalPrice = 0.0F;

        int totalQuantity  = 0;

        for (Cart item : cartItems) {
            totalQuantity += item.getQuantity();
            subTotalPrice += item.getSubTotalPrice();
            totalPrice += item.getSubTotalPrice() + shippingPrice;
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subTotalPrice", subTotalPrice);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("shippingPrice", shippingPrice);
        model.addAttribute("totalQuantity", totalQuantity);

        return "cart/cart_items";
    }

    private Customer getCustomer(HttpServletRequest request) {
        String customerEmail = customerService.getEmailFromCustomer(request);
        return customerService.getCustomerByEmail(customerEmail);
    }

}