package WebShopApp.Application.Service;

import WebShopApp.Application.Entity.Product;
import WebShopApp.Application.Exceptions.ProductNotFoundException;
import WebShopApp.Application.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ProductService {

    public static final int PRODUCT_PER_PAGE = 5;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @PostMapping("/products/save")
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setCreatedTime(new Date());
        }

        if (product.getAlias() == null || product.getAlias().isEmpty()) {
            String defaultAlias = product.getName().replaceAll(" ", "_");
            product.setAlias(defaultAlias);
        } else {
            product.setAlias(product.getAlias().replaceAll(" ", "_"));
        }

        product.setUpdatedTime(new Date());

        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) throws ProductNotFoundException {
        Long countById = productRepository.countById(id);

        if (countById == null || countById == 0) {
            throw new ProductNotFoundException("Could not find aby product with ID " + id);
        }

        productRepository.deleteById(id);
    }

    public void updateProductStatus(Integer id, boolean status) {
        productRepository.updateStatus(id, status);
    }

    public Product getProduct(Integer id) throws ProductNotFoundException {
        try {
            return productRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ProductNotFoundException("Could not find any product with ID " + id);
        }
    }

    public Page<Product> listByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, PRODUCT_PER_PAGE);
        return productRepository.findAll(pageable);
    }
}