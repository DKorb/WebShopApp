package com.webshopapp.adminpanel.controller.product;


import com.webshopapp.adminpanel.controller.file.FileUploadUtil;
import com.webshopapp.common.entity.brand.Brand;
import com.webshopapp.common.entity.product.Product;
import com.webshopapp.common.exceptions.ProductNotFoundException;
import com.webshopapp.adminpanel.service.BrandService;
import com.webshopapp.adminpanel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

/*    @GetMapping("/products")
    public String listAllProducts(Model model) {
        List<Product> productList = productService.listAllProducts();

        model.addAttribute("productList", productList);

        return "products/products";
    }*/

    @GetMapping("/products")
    public String listFirstPage(Model model) {
        return listByPage(1, model);
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
    public String saveProduct(Product product,
                              @RequestParam("fileImage") MultipartFile multipartFile,
                              @RequestParam(name = "detailNames", required = false) String[] detailNames,
                              @RequestParam(name = "detailValues", required = false) String[] detailValues,
                              @RequestParam(name = "detailIDs", required = false) String[] detailIDs,
                              RedirectAttributes redirectAttributes) throws IOException {

        setImageName(multipartFile, product);
        setProductDetails(detailNames, detailValues, detailIDs, product);

        Product savedProduct = productService.save(product);

        saveUploadedImage(multipartFile, savedProduct);

        redirectAttributes.addFlashAttribute("message",
                "The product has been saved successfully.");

        return "redirect:/products";
    }

    private void setProductDetails(String[] detailNames,
                                   String[] detailValues,
                                   String[] detailIDs,
                                   Product product) {
        if (detailNames == null || detailNames.length == 0) return;

        for (int count = 0; count < detailNames.length; count++) {
            String name = detailNames[count];
            String value = detailValues[count];
            Integer id = Integer.parseInt(detailIDs[count]);

            if (id != 0) {
                product.addDetails(id, name, value);
            } else if (!name.isEmpty() && !value.isEmpty()) {
                product.addDetails(name, value);
            }
        }
    }

    private void setImageName(MultipartFile multipartFile, Product product) {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setImage(fileName);
        }
    }

    private void saveUploadedImage(MultipartFile multipartFile, Product savedProduct) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "product-images/" + savedProduct.getId();

            FileUploadUtil.cleanDirectory(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable(name = "id") Integer id,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.getProduct(id);

            List<Product> listAllProducts = productService.listAllProducts();
            List<Brand> listBrands = brandService.listAllBrands();

            model.addAttribute("product", product);
            model.addAttribute("listBrands", listBrands);
            model.addAttribute("listAllProducts", listAllProducts);
            model.addAttribute("pageTitle", "Edit Product (ID: " + id + ")");


            return "products/product_form";
        } catch (ProductNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/products";
        }
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

    @GetMapping("/products/page/{pageNumber}")
    public String listByPage(@PathVariable(name = "pageNumber") int pageNumber, Model model) {

        Page<Product> page = productService.listByPage(pageNumber);
        List<Product> productList = page.getContent();

        long startCount = ((long) (pageNumber - 1) * ProductService.PRODUCT_PER_PAGE + 1);
        long endCount = startCount + ProductService.PRODUCT_PER_PAGE - 1;

        if (endCount > page.getTotalElements()) {
            page.getTotalElements();
        }

        model.addAttribute("currentProductPage", pageNumber);
        model.addAttribute("totalProductPages", page.getTotalPages());
        model.addAttribute("productList", productList);

        return "products/products";
    }
}