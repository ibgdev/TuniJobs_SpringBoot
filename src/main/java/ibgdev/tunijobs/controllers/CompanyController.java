package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/company")
public class CompanyController {
    @Autowired
    private CompanyService comapnyService;

    @GetMapping("/retrieve-all-companies")
    public String getAllCompanies(Model model) {
        model.addAttribute("listcompany", comapnyService.findAll());
        return "company/listcompany";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("company", new Company());
        return "company/addcompany";
    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute Company company, RedirectAttributes redirectAttributes) {
        comapnyService.addCompany(company);
        redirectAttributes.addFlashAttribute("message", "Company added successfully");
        return "redirect:/admin/company/retrieve-all-companies";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") long id, Model model) {
        model.addAttribute("company", comapnyService.findCompanyById(id));

        return "company/updateCompany";
    }

    @PostMapping("/updateCompany")
    public String updateCategory(@ModelAttribute Company company, RedirectAttributes redirectAttributes) {
        comapnyService.updateCompany(company.getId(), company);
        redirectAttributes.addFlashAttribute("message", "Company updated successfully");
        return "redirect:/admin/company/retrieve-all-companies";
    }

    @GetMapping("/delete")
    public String deleteCompany(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        comapnyService.DeleteCompany(id);
        redirectAttributes.addFlashAttribute("message", "Company deleted successfully");
        return "redirect:/admin/company/retrieve-all-companies";
    }
}
