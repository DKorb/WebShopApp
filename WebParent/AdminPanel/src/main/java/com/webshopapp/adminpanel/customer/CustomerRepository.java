package com.webshopapp.adminpanel.customer;

import com.webshopapp.common.entity.customer.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    Long countById(Integer id);

    @Query("UPDATE Customer customer SET customer.status = ?2 WHERE customer.id = ?1")
    @Modifying
    void updateStatus(Integer id, boolean status);

}