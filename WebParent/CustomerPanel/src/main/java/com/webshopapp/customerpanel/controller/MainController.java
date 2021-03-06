package com.webshopapp.customerpanel.controller;

import com.webshopapp.common.entity.category.Category;
import com.webshopapp.customerpanel.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {


    private CategoryService categoryService;

    @GetMapping
    public String viewHomePage(Model model) {
        List<Category> listCategories = categoryService.listAllCategories();
        model.addAttribute("listCategories", listCategories);

        return "index";
    }
}