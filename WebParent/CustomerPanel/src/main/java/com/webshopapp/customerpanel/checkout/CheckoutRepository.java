package com.webshopapp.customerpanel.checkout;

import com.webshopapp.common.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Order, Integer> {


}