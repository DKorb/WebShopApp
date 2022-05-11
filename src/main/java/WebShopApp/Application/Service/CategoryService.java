package WebShopApp.Application.Service;

import WebShopApp.Application.Entity.Category;
import WebShopApp.Application.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAllCategories() {
        List<Category> listRootCategories = categoryRepository.listRootCategories();
        return (List<Category>) categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
