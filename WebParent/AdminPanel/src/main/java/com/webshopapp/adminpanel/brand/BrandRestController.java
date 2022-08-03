package com.webshopapp.adminpanel.brand;


import com.webshopapp.adminpanel.category.dto.CategoryDTO;
import com.webshopapp.common.entity.brand.Brand;
import com.webshopapp.common.entity.category.Category;
import com.webshopapp.common.exceptions.BrandNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class BrandRestController {

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