package com.webshopapp.common.entity.cart;

import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Transient
    private final float shippingCost = 20;

    @Transient
    public float getSubTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Transient
    public float getShippingCost() {
        return shippingCost;
    }
}