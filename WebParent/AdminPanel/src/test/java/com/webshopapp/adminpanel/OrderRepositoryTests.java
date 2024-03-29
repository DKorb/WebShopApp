package com.webshopapp.adminpanel;

import com.webshopapp.adminpanel.order.OrderRepository;
import com.webshopapp.common.entity.customer.Customer;
import com.webshopapp.common.entity.order.Order;
import com.webshopapp.common.entity.order.OrderDetail;
import com.webshopapp.common.entity.order.OrderStatus;
import com.webshopapp.common.entity.order.PaymentMethod;
import com.webshopapp.common.entity.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestPropertySource(properties = {
        "MYSQLDB_USER=root",
        "MYSQLDB_ROOT_PASSWORD=root",
        "MYSQLDB_DATABASE=webshopdb",
        "MYSQLDB_LOCAL_PORT=3306"})
@Slf4j
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAddNewOrder() {

        Customer customer = testEntityManager.find(Customer.class, 1);

        Product firstProduct = testEntityManager.find(Product.class, 1);
        Product secondProduct = testEntityManager.find(Product.class, 2);

        Order order = new Order();

        order.setCustomer(customer);
        order.setFirstName(customer.getFirstName());
        order.setLastName(customer.getLastName());
        order.setPhoneNumber(customer.getPhoneNumber());
        order.setAddress(customer.getAddress());
        order.setCity(customer.getCity());
        order.setProductCost(firstProduct.getPrice() + secondProduct.getPrice());
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setOrderStatus(OrderStatus.NEW);
        order.setOrderTime(new Date());

        OrderDetail firstOrder = new OrderDetail();

        firstOrder.setProduct(firstProduct) ;
        firstOrder.setProductCost(firstProduct.getPrice());
        firstOrder.setOrder(order);
        firstOrder.setQuantity(1);

        OrderDetail secondOrder = new OrderDetail();

        secondOrder.setProduct(secondProduct) ;
        secondOrder.setProductCost(secondProduct.getPrice());
        secondOrder.setOrder(order);
        secondOrder.setQuantity(1);

        order.getOrderDetails().add(firstOrder);
        order.getOrderDetails().add(secondOrder);

        Order newOrder = orderRepository.save(order);

        assertThat(newOrder.getId()).isGreaterThan(0);

    }

}