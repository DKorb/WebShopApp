package com.webshopapp.adminpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.webshopapp.common.entity"})
public class AdminPanelApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminPanelApplication.class, args);
    }

}
