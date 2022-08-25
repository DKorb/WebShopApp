package com.webshopapp.adminpanel.order;

import com.webshopapp.common.entity.order.Order;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/orders")
    public String listFirstPage(Model model) {
        return listByPage(1, model);
    }

    @GetMapping("/orders/page/{pageNumber}")
    public String listByPage(@PathVariable(name = "pageNumber") int pageNumber, Model model) {

        Page<Order> page = orderService.listByPage(pageNumber);
        List<Order> orderList = page.getContent();

        long startCount = ((long) (pageNumber - 1) * OrderService.ORDERS_PER_PAGE + 1);
        long endCount = startCount + OrderService.ORDERS_PER_PAGE - 1;

        if (endCount > page.getTotalElements()) {
            page.getTotalElements();
        }

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("orderList", orderList);

        return "orders/orders";
    }

}