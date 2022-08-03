package com.webshopapp.customerpanel.customer;

import com.webshopapp.common.entity.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;


    @GetMapping("/register")
    public String newCustomer(Model model) {

        model.addAttribute("customer", new Customer());

        return "register/register_form";
    }


    @PostMapping("/create_customer")
    public String createCustomer(Customer customer)  {

        customerService.registerCustomer(customer);

        return "register/register_success";
    }

}