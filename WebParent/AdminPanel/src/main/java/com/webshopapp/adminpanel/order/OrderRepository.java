package com.webshopapp.adminpanel.order;

import com.webshopapp.common.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {


}