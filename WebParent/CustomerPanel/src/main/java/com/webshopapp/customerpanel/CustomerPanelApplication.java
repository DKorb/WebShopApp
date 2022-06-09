package com.webshopapp.customerpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.webshopapp.common.entity"})
public class CustomerPanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerPanelApplication.class, args);
	}

}
