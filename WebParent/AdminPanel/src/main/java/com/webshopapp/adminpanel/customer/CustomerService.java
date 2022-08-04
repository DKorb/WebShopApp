package com.webshopapp.adminpanel.customer;

import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.user.User;
import com.webshopapp.common.exceptions.CustomerNotFoundException;
import com.webshopapp.common.exceptions.UserNotFoundException;
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
public class CustomerService {

    public static int CUSTOMERS_PER_PAGE = 10;

    private CustomerRepository customerRepository;

    private PasswordEncoder passwordEncoder;

    private void encodePassword(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }

    public Page<Customer> listByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, CUSTOMERS_PER_PAGE);
        return customerRepository.findAll(pageable);
    }

    public void deleteCustomer(Integer id) throws CustomerNotFoundException {
        Long countById = customerRepository.countById(id);

        if (countById == null || countById == 0) {
            throw new CustomerNotFoundException("Could not find aby customer with ID " + id);
        }

        customerRepository.deleteById(id);
    }

    public void updateCustomerStatus(Integer id, boolean status) {
        customerRepository.updateStatus(id, status);
    }
}