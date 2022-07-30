package com.webshopapp.customerpanel.product;

import com.webshopapp.common.entity.category.Category;
import com.webshopapp.common.entity.product.Product;
import com.webshopapp.common.exceptions.CategoryNotFoundException;
import com.webshopapp.common.exceptions.ProductNotFoundException;
import com.webshopapp.customerpanel.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {


    private CategoryService categoryService;

    private ProductService productService;

    @GetMapping("/categories/{category_alias}")
    public String viewCategoryFirstPage(@PathVariable("category_alias") String alias,
                                        Model model) throws CategoryNotFoundException {
        return viewCategoryByPage(alias, 1, model);
    }

    @GetMapping("/categories/{category_alias}/page/{pageNumber}")
    public String viewCategoryByPage(@PathVariable("category_alias") String alias,
                                     @PathVariable("pageNumber") int pageNumber,
                                     Model model) throws CategoryNotFoundException {

        Category category = categoryService.getCategory(alias);

        Page<Product> productPage = productService.listByCategory(pageNumber, category.getId());
        List<Product> productList = productPage.getContent();

        long startCount = ((long) (pageNumber - 1) * ProductService.PRODUCTS_PER_PAGE + 1);
        long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;

        if (endCount > productPage.getTotalElements()) {
            productPage.getTotalElements();
        }

        model.addAttribute("currentProductPage", pageNumber);
        model.addAttribute("totalProductPages", productPage.getTotalPages());
        model.addAttribute("productList", productList);
        model.addAttribute("pageTitle", category.getName() + " - WebShop");
        model.addAttribute("category", category);


        return "product/products_by_category";
    }

    @GetMapping("/product/{product_alias}")
    public String viewProductDetail(@PathVariable("product_alias") String alias,
                                    Model model) throws ProductNotFoundException {

        Product product = productService.getProduct(alias);

        model.addAttribute("pageTitle", product.getName() + " - WebShop");
        model.addAttribute("product", product);

        return "product/product_detail";

    }

    @GetMapping("/search")
    public String searchFirstPage(@Param("keyword") String keyword,
                                  Model model) {
        return searchProductsByPage(keyword, model, 1);
    }

    @GetMapping("/search/page/{pageNumber}")
    public String searchProductsByPage(@Param("keyword") String keyword,
                                Model model,
                                @PathVariable("pageNumber") int pageNumber) {

        var pageProducts = productService.search(keyword, pageNumber);
        var productResults = pageProducts.getContent();

        long startCount = ((long) (pageNumber - 1) * ProductService.SEARCH_RESULT_PER_PAGE + 1);
        long endCount = startCount + ProductService.SEARCH_RESULT_PER_PAGE - 1;

        if (endCount > pageProducts.getTotalElements()) {
            pageProducts.getTotalElements();
        }

        model.addAttribute("currentProductPage", pageNumber);
        model.addAttribute("totalProductPages", pageProducts.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("productResults", productResults);

        return "product/product_result";
    }
}