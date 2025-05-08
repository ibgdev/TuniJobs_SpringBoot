package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.repository.UserRepository;
import ibgdev.tunijobs.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entreprise")
public class EntrepriseController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String entrepriseInfos(Model model, Authentication authentication) {
        String username = authentication.getName();
        Company company = companyService.findCompanyByResponsable(userRepository.findUserByEmail(username));
        model.addAttribute("company", company);
        return "entreprise/details";
    }

    @GetMapping("/joboffers")
    public String details(Model model) {
        return "entreprise/joboffers";
    }
}
