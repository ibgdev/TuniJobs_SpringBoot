package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.entity.Roles;
import ibgdev.tunijobs.services.CompanyService;
import ibgdev.tunijobs.services.UserService;
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

    @Autowired
    private UserService userService;

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

    @GetMapping("/assign-responsible-page")
    public String showAssignResponsiblePage(@RequestParam("id") Long companyId, Model model) {
        Company company = comapnyService.findCompanyById(companyId);
        model.addAttribute("company", company);
        model.addAttribute("users", userService.FindUserByRole(Roles.valueOf("CANDIDATE"))); // Load all existing users
        return "company/assign_responsible";
    }

    @PostMapping("/assign-responsible")
    public String assignResponsible(@RequestParam Long companyId,
                                    @RequestParam Long userId,
                                    RedirectAttributes redirectAttributes) {
        comapnyService.assignResponsible(companyId, userId);
        redirectAttributes.addFlashAttribute("message", "Responsible assigned successfully!");
        return "redirect:/admin/company/retrieve-all-companies";
    }

}
