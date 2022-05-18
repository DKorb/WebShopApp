package WebShopApp.Application.Controller;

import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public String listAllBrands(Model model) {
        List<Brand> brandList = brandService.listBrands();
        model.addAttribute("brandList", brandList);

        return "brands";
    }
}
