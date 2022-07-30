package com.webshopapp.customerpanel;

import com.webshopapp.common.entity.product.Product;
import com.webshopapp.customerpanel.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void getProductByAlias() {
        String alias = "Lenovo_Flex_3i_Chromebook";
        Product productByAlias = productRepository.getProductByAlias(alias);

        assertThat(productByAlias).isNotNull();
    }
}