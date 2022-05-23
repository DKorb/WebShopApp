package WebShopApp.Application.Controller;

import WebShopApp.Application.Entity.Product;
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

    @GetMapping("/products")
    public String listAllProducts(Model model) {
        List<Product> productList = productService.listAllProducts();

        model.addAttribute("productList", productList);

        return "products/products";
    }
}
