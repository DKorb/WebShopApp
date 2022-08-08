package com.webshopapp.customerpanel.security;

import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.customerpanel.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityCustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer != null) {
            return new SecurityCustomerDetails(customer);
        } else throw new UsernameNotFoundException("Can't find customer with email  " + email);
    }

}