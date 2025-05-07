package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/condidate")
public class CondidateController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public String companies(Model model) {
        model.addAttribute("listcompanies", companyService.findAll() );
        return "condidate/companies";
    }


}
