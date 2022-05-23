package WebShopApp.Application.Controller;

import WebShopApp.Application.Controller.File.FileUploadUtil;
import WebShopApp.Application.Entity.Brand;
import WebShopApp.Application.Entity.Category;
import WebShopApp.Application.Exceptions.BrandNotFoundException;
import WebShopApp.Application.Service.BrandService;
import WebShopApp.Application.Service.CategoryService;
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
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/brands")
    public String listFirstPage(Model model) {
        return listByPage(1, model);
    }

    @GetMapping("/brands/new")
    public String newBrand(Model model) {
        List<Category> listCategories = categoryService.listAllCategories();

        model.addAttribute("listCategories", listCategories);
        model.addAttribute("brand", new Brand());
        model.addAttribute("pageTitle", "Create New Brand");

        return "brands/brands_form";
    }

    @PostMapping("/brands/save")
    public String savedBrand(Brand brand, @RequestParam("fileImage") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            brand.setLogo(fileName);

            Brand savedBrand = brandService.save(brand);
            String uploadDir = "brand-images/" + savedBrand.getId();

            FileUploadUtil.cleanDirectory(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            brandService.save(brand);
        }

        redirectAttributes.addFlashAttribute("message", "The brand has been saved successfully");
        return "redirect:/brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String editBrand(@PathVariable(name = "id") Integer id,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        try {
            Brand brand = brandService.get(id);
            List<Brand> listBrands = brandService.listBrands();
            List<Category> listCategories = categoryService.listAllCategories();

            model.addAttribute("brand", brand);
            model.addAttribute("listBrands", listBrands);
            model.addAttribute("listCategories", listCategories);
            model.addAttribute("pageTitle", "Edit Brand (ID: " + id + ")");

            return "brands/brands_form";
        } catch (BrandNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/brands";
        }
    }

    @GetMapping("/brands/delete/{id}")
    public String deleteBrand(@PathVariable(name = "id") Integer id,
                              RedirectAttributes redirectAttributes) {
        try {
            brandService.deleteBrand(id);
            String brandDir = "/brand-images/" + id;
            FileUploadUtil.cleanDirectory(brandDir);

            redirectAttributes.addFlashAttribute("message", "The brand ID " + id + " has been deleted successfully");
        } catch (BrandNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/brands";
    }

    @GetMapping("/brands/page/{pageNumber}")
    public String listByPage(@PathVariable(name = "pageNumber") int pageNumber, Model model) {

        Page<Brand> page = brandService.listByPage(pageNumber);
        List<Brand> brandList = page.getContent();

        long startCount = ((long) (pageNumber - 1) * BrandService.BRAND_PER_PAGE + 1);
        long endCount = startCount + BrandService.BRAND_PER_PAGE - 1;

        if (endCount > page.getTotalElements()) {
            page.getTotalElements();
        }

        model.addAttribute("currentBrandPage", pageNumber);
        model.addAttribute("totalBrandPages", page.getTotalPages());
        model.addAttribute("brandList", brandList);

        return "brands/brands";
    }
}
