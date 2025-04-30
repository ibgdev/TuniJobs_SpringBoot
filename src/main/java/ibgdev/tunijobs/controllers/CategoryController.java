package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.Category;
import ibgdev.tunijobs.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/retrieve-all-categories")
    public String getAllCategories(Model model) {
        model.addAttribute("listcategory", categoryService.findAll());
        return "category/listcategory";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/addcategory";
    }

    @PostMapping("/addCategory")
    public String AddCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        categoryService.addCategory(category);
        redirectAttributes.addFlashAttribute("message", "Category added successfully");
        return "redirect:/admin/category/retrieve-all-categories";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") long id, Model model) {
        model.addAttribute("category", categoryService.findCategoryById(id));

        return "category/updatecategory";
    }

    @PostMapping("/updatecategory")
    public String updateCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        categoryService.updateCategory(category.getId(), category);
        redirectAttributes.addFlashAttribute("message", "Category updated successfully");
        return "redirect:/admin/category/retrieve-all-categories";
    }

    @GetMapping("/delete")
    public  String deleteCategory(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("message", "Category deleted successfully");
        return "redirect:/admin/category/retrieve-all-categories";
    }
}
