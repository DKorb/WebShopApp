package com.webshopapp.adminpanel.customer;

import com.webshopapp.common.entity.customer.Customer;
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

}