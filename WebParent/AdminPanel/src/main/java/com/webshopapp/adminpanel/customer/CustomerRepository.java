package com.webshopapp.adminpanel.customer;

import com.webshopapp.common.entity.customer.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

}