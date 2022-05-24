package WebShopApp.Application.Controller;

import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Entity.Category;
import WebShopApp.Application.Exceptions.BrandNotFoundException;
import WebShopApp.Application.Service.BrandService;
import WebShopApp.Application.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class BrandRestController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands/{id}/categories")
    public List<CategoryDTO> listCategoriesByBrand(@PathVariable(name = "id") Integer brandId) throws BrandNotFoundException {
        List<CategoryDTO> listCategories = new ArrayList<>();

        Brand brand = brandService.get(brandId);
        Set<Category> categories = brand.getCategories();
        for (Category category : categories) {
            CategoryDTO dto = new CategoryDTO(category.getId(), category.getName());
            listCategories.add(dto);
        }
        return listCategories;
    }
}