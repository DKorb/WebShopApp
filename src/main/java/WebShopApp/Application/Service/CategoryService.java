package WebShopApp.Application.Service;

import WebShopApp.Application.Entity.Category;
import WebShopApp.Application.Exceptions.CategoryNotFoundException;
import WebShopApp.Application.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }


    public Category getCategory(Integer id) throws CategoryNotFoundException {
        try {
            return categoryRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new CategoryNotFoundException("Could not find aby category with ID " + id);
        }
    }

    public void deleteCategory(Integer id) throws CategoryNotFoundException {
        Long countById = categoryRepository.countById(id);

        if (countById == null || countById == 0) {
            throw new CategoryNotFoundException("Could not find aby category with ID " + id);
        }

        categoryRepository.deleteById(id);
    }

    public void updateCategoryStatus(Integer id, boolean status) {
        categoryRepository.updateStatus(id, status);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
