package WebShopApp.Application.Service;

import WebShopApp.Application.Entity.Product;
import WebShopApp.Application.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
}
