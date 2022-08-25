package com.webshopapp.common.entity.customer;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 48, nullable = false, unique = true)
    private String email;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(name = "first_name", length = 24, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 24, nullable = false)
    private String lastName;

    private boolean status;

    @Column(name = "phone_number", nullable = false, length = 12)
    private String phoneNumber;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(nullable = false, length = 64)
    private String city;

    @Column(nullable = false, length = 64)
    private String address;

    @Transient
    public String getCustomerFullName() {
        return firstName + " " + lastName;
    }

    @Transient
    public String getCustomerFullAddress() {
        return address + ", " + city;
    }

}