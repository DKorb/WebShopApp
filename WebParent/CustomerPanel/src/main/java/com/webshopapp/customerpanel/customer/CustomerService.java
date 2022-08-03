package com.webshopapp.customerpanel.customer;

import com.webshopapp.common.entity.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    private PasswordEncoder passwordEncoder;

    public void registerCustomer(Customer customer) {
        encodePassword(customer);
        customer.setStatus(true);
        customer.setCreatedTime(new Date());

        customerRepository.save(customer);
    }

    private void encodePassword(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }

}