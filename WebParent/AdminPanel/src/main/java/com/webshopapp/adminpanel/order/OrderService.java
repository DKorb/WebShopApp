package com.webshopapp.adminpanel.order;

import com.webshopapp.common.entity.order.Order;
import com.webshopapp.common.exceptions.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    public static int ORDERS_PER_PAGE = 10;

    private OrderRepository orderRepository;

    public Page<Order> listByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, ORDERS_PER_PAGE);
        return orderRepository.findAll(pageable);
    }

    public void deleteOrder(Integer id) throws OrderNotFoundException {
        Long countById = orderRepository.countById(id);

        if (countById == null || countById == 0) {
            throw new OrderNotFoundException("Could not find aby order with ID " + id);
        }

        orderRepository.deleteById(id);
    }

    public Order getOrder(Integer orderId) throws OrderNotFoundException {
        try {
            return orderRepository.findById(orderId).get();
        } catch (NoSuchElementException ex) {
            throw new OrderNotFoundException("Could not find any orders with ID " + orderId);
        }
    }

}