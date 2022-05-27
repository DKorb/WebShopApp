package WebShopApp.Application.Controller;

import WebShopApp.Application.Controller.File.FileUploadUtil;
import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Entity.Product;
import WebShopApp.Application.Exceptions.ProductNotFoundException;
import WebShopApp.Application.Service.BrandService;
import WebShopApp.Application.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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

    @PostMapping("/products/save")
    public String saveProduct(Product product, @RequestParam("fileImage") MultipartFile multipartFile,
                              RedirectAttributes redirectAttributes) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setImage(fileName);

            Product savedProduct = productService.save(product);
            String uploadDir = "product-images/" + savedProduct.getId();

            FileUploadUtil.cleanDirectory(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            productService.save(product);
        }

        productService.save(product);
        redirectAttributes.addFlashAttribute("message",
                "The product has been saved successfully.");

        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id,
                                RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("message", "The product ID " + id
                    + " has been deleted successfully");
        } catch (ProductNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/status/{status}")
    public String changeProductsStatus(@PathVariable("id") Integer id,
                                       @PathVariable("status") boolean status,
                                       RedirectAttributes redirectAttributes) {
        productService.updateProductStatus(id, status);

        String message = status ? "enabled" : "disabled";
        redirectAttributes.addFlashAttribute("message", "The product ID " + id +
                " has been " + message);
        return "redirect:/products";
    }
}