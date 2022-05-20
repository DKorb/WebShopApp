package WebShopApp.Application.Controller;

import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Entity.Category;
import WebShopApp.Application.Service.BrandService;
import WebShopApp.Application.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/brands")
    public String listAllBrands(Model model) {
        List<Brand> brandList = brandService.listBrands();
        model.addAttribute("brandList", brandList);

        return "brands";
    }

    @GetMapping("/brands/new")
    public String newBrand(Model model) {
        List<Category> listCategories = categoryService.listAllCategories();

        model.addAttribute("listCategories", listCategories);
        model.addAttribute("brand", new Brand());
        model.addAttribute("pageTitle", "Create New Brand");

        return "brands_form";
    }
}
