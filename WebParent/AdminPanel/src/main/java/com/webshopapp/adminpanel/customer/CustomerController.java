package com.webshopapp.adminpanel.customer;


import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.exceptions.CustomerNotFoundException;
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

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Integer id,
                                 RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("message", "The customer ID " + id
                    + " has been deleted successfully");
        } catch (CustomerNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}/status/{status}")
    public String changeStatus(@PathVariable("id") Integer id,
                               @PathVariable("status") boolean status,
                               RedirectAttributes redirectAttributes) {

        customerService.updateCustomerStatus(id, status);

        String message = status ? "enabled" : "disabled";
        redirectAttributes.addFlashAttribute("message", "The customer ID " + id +
                " has been " + message);

        return "redirect:/customers";
    }


}