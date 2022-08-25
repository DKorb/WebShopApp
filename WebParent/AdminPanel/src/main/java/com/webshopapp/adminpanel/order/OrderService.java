package com.webshopapp.adminpanel.order;

import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.order.Order;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    public static int ORDERS_PER_PAGE = 10;

    private OrderRepository orderRepository;

    private PasswordEncoder passwordEncoder;

    private void encodePassword(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }

    public Page<Order> listByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, ORDERS_PER_PAGE);
        return orderRepository.findAll(pageable);
    }



}