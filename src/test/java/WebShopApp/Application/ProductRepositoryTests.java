package WebShopApp.Application;

import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Entity.Category;
import WebShopApp.Application.Entity.Product;
import WebShopApp.Application.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void testCreateProduct() {
        Brand brand = testEntityManager.find(Brand.class, 7);
        Category category = testEntityManager.find(Category.class, 2);

        Product product = new Product();
        product.setName("Sony Alpha 7 IV");
        product.setAlias("sony alpha 7 iv");
        product.setShortDescription("test short description, test short description, " +
                "test short description, test short description, test short description," +
                "test short description, test short description, test short description,");

        product.setFullDescription("test full description, test full description," +
                "test full description, test full description, test full description, " +
                "test full description, test full description, test full description, " +
                "test full description, test full description, test full description, " +
                "test full description, test full description, test full description, " +
                "test full description, test full description, test full description, ");

        product.setBrand(brand);
        product.setCategory(category);
        product.setPrice(2698);
        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());
        product.setImage("main_image.png");

        Product testProduct = productRepository.save(product);

        assertThat(testProduct).isNotNull();
        assertThat(testProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllProducts() {
        Iterable<Product> allProducts = productRepository.findAll();
        allProducts.forEach(System.out::println);
    }

    @Test
    public void testGetProduct() {
        Integer productID = 1;
        Product foundProduct = productRepository.findById(productID).get();
        System.out.println(foundProduct);
        assertThat(foundProduct).isNotNull();
    }
}