package com.webshopapp.adminpanel.customer;


import com.webshopapp.common.entity.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/customers")
    public String listFirstPage(Model model) {
        return listByPage(1, model);
    }

    @GetMapping("/customers/page/{pageNumber}")
    public String listByPage(@PathVariable(name = "pageNumber") int pageNumber, Model model) {

        Page<Customer> page = customerService.listByPage(pageNumber);
        List<Customer> customersList = page.getContent();

        long startCount = ((long) (pageNumber - 1) * CustomerService.CUSTOMERS_PER_PAGE + 1);
        long endCount = startCount + CustomerService.CUSTOMERS_PER_PAGE - 1;

        if (endCount > page.getTotalElements()) {
            page.getTotalElements();
        }

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("customersList", customersList);

        return "customers/customers";
    }

}