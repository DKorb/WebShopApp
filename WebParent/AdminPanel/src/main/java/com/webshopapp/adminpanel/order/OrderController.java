package com.webshopapp.adminpanel.order;

import com.webshopapp.common.entity.order.Order;
import com.webshopapp.common.exceptions.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable(name = "id") Integer id,
                                 RedirectAttributes redirectAttributes) {
        try {
            orderService.deleteOrder(id);
            redirectAttributes.addFlashAttribute("message", "The order ID " + id
                    + " has been deleted successfully");
        } catch (OrderNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/orders";
    }

    @GetMapping("/orders/detail/{id}")
    public String viewOrderDetails(@PathVariable("id") Integer id,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        try {
            Order order = orderService.getOrder(id);
            model.addAttribute(order);
            return "orders/order_details";
        } catch (OrderNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/orders";
        }
    }

}