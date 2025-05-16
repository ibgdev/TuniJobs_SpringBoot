package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.services.CategoryService;
import ibgdev.tunijobs.services.CompanyService;
import ibgdev.tunijobs.services.JobOfferService;
import ibgdev.tunijobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin")
    public String AdminDashboard(Model model) {
        int nbUser = userService.findAll().size();
        int nbJobs = jobOfferService.findAll().size();
        int nbCompanies = companyService.findAll().size();
        int nbCategories = categoryService.findAll().size();


        model.addAttribute("nbUser", nbUser);
        model.addAttribute("nbJobs", nbJobs);
        model.addAttribute("nbCompanies", nbCompanies);
        model.addAttribute("nbCategories", nbCategories);
        return "dashboard";
    }
}
