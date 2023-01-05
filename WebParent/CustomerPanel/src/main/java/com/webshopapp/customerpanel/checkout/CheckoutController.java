package com.webshopapp.customerpanel.checkout;

import com.webshopapp.common.entity.cart.Cart;
import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.order.Order;
import com.webshopapp.common.entity.order.OrderDetail;
import com.webshopapp.common.entity.order.OrderStatus;
import com.webshopapp.common.entity.order.PaymentMethod;
import com.webshopapp.customerpanel.cart.CartService;
import com.webshopapp.customerpanel.customer.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class CheckoutController {

    private CheckoutService checkoutService;

    private CustomerService customerService;

    private CartService cartService;


    @GetMapping("/checkout")
    public String showCheckoutPage(Model model, HttpServletRequest request) {
        Customer customer = getCustomer(request);
        List<Cart> cartItems = cartService.listCartItems(customer);

        Checkout checkout = checkoutService.prepareCheckout(cartItems);
        Order order = new Order();

        model.addAttribute("checkout", checkout);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("customer", customer);
        model.addAttribute("order", order);

        return "checkout/checkout";
    }

    private Customer getCustomer(HttpServletRequest request) {
        String customerEmail = customerService.getEmailFromCustomer(request);
        return customerService.getCustomerByEmail(customerEmail);
    }

    @PostMapping("/checkout/save")
    public String saveOrder(Order order,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        Customer customer = getCustomer(request);

        checkoutService.saveCheckoutOrder(customer, order);

        redirectAttributes.addFlashAttribute("message", "The order has been accepted!");
        return "redirect:/";
    }


}