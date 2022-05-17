package WebShopApp.Application.Controller;

import WebShopApp.Application.Controller.File.FileUploadUtil;
import WebShopApp.Application.Entity.Category;
import WebShopApp.Application.Exceptions.CategoryNotFoundException;
import WebShopApp.Application.Service.CategoryService;
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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String listAllCategories(Model model) {
        List<Category> categoryList = categoryService.listAllCategories();
        model.addAttribute("categoryList", categoryList);

        return "categories";
    }

    @GetMapping("/categories/new")
    public String newCategory(Model model) {
        List<Category> listCategoriesInForm = categoryService.listAllCategories();

        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("listCategoriesInForm", listCategoriesInForm);
        model.addAttribute("pageTitle", "Create New Category");

        return "category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category,
                               @RequestParam("fileImage") MultipartFile multipartFile,
                               RedirectAttributes redirectAttributes) throws IOException {


        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            category.setImage(fileName);

            Category savedCategory = categoryService.save(category);
            String uploadDir = "category-images/" + savedCategory.getId();

            FileUploadUtil.cleanDirectory(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            categoryService.save(category);
        }
        redirectAttributes.addFlashAttribute("message",
                "The category has been saved successfully");
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable(name = "id") Integer id,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.getCategory(id);
            List<Category> listAllCategories = categoryService.listAllCategories();

            model.addAttribute("category", category);
            model.addAttribute("listAllCategories", listAllCategories);
            model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");

            return "category_form";
        } catch (CategoryNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/categories";
        }
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Integer id,
                                 RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("message", "The category ID " + id
                    + " has been deleted successfully");
        } catch (CategoryNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}/status/{status}")
    public String changeCategoryStatus(@PathVariable("id") Integer id,
                                       @PathVariable("status") boolean status,
                                       RedirectAttributes redirectAttributes) {
        categoryService.updateCategoryStatus(id, status);

        String message = status ? "enabled" : "disabled";
        redirectAttributes.addFlashAttribute("message", "The category ID " + id +
                " has been " + message);
        return "redirect:/categories";
    }

}