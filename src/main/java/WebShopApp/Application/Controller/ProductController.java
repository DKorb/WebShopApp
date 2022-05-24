package WebShopApp.Application.Controller;

import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Entity.Product;
import WebShopApp.Application.Service.BrandService;
import WebShopApp.Application.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/products")
    public String listAllProducts(Model model) {
        List<Product> productList = productService.listAllProducts();

        model.addAttribute("productList", productList);

        return "products/products";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model) {
        List<Brand> listBrands = brandService.listAllBrands();

        Product product = new Product();
        product.setStatus(true);
        product.setInStock(true);

        model.addAttribute("product", product);
        model.addAttribute("listBrands", listBrands);
        model.addAttribute("pageTitle", "Create New Product");

        return "products/product_form";
    }
}