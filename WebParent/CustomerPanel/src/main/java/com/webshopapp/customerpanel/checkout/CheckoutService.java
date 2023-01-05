package com.webshopapp.customerpanel.checkout;


import com.webshopapp.common.entity.cart.Cart;
import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.order.Order;
import com.webshopapp.common.entity.order.OrderDetail;
import com.webshopapp.common.entity.order.OrderStatus;
import com.webshopapp.common.entity.order.PaymentMethod;
import com.webshopapp.customerpanel.cart.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CheckoutService {

    private CheckoutRepository checkoutRepository;

    private CartService cartService;

    public Checkout prepareCheckout(List<Cart> cartItems) {
        Cart cart = new Cart();
        Checkout checkout = new Checkout();
        float productTotal = calculateProductTotal(cartItems);

        checkout.setProductCost(productTotal);
        checkout.setShippingCost(cart.getShippingCost());

        return checkout;
    }

    private float calculateProductTotal(List<Cart> cartItems) {

        float total = 0.0f;

        for (Cart item : cartItems) {
            total += item.getSubTotalPrice() + item.getShippingCost();
        }
        return total;

    }

    public void saveCheckoutOrder(Customer customer, Order order) {

        List<Cart> cartItems = cartService.listCartItems(customer);
        Checkout checkout = prepareCheckout(cartItems);

        order.setCustomer(customer);
        order.setFirstName(customer.getFirstName());
        order.setLastName(customer.getLastName());
        order.setPhoneNumber(customer.getPhoneNumber());
        order.setAddress(customer.getAddress());
        order.setCity(customer.getCity());
        order.setProductCost(checkout.getProductCost());
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setOrderStatus(OrderStatus.NEW);
        order.setOrderTime(new Date());

        for (Cart cart : cartItems) {

            OrderDetail orderDetail = OrderDetail.builder()
                    .productCost(cart.getSubTotalPrice())
                    .quantity(cart.getQuantity())
                    .product(cart.getProduct())
                    .order(order)
                    .build();

            order.getOrderDetails().add(orderDetail);

        }

        checkoutRepository.save(order);
    }

}