package com.webshopapp.common.entity.order;


import com.webshopapp.common.entity.customer.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 24, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 24, nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false, length = 12)
    private String phoneNumber;

    @Column(nullable = false, length = 64)
    private String city;

    @Column(nullable = false, length = 64)
    private String address;

    private Date orderTime;

    private float productCost;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();


}