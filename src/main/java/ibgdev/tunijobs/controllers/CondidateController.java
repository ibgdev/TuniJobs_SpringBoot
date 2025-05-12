package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.entity.JobOffer;
import ibgdev.tunijobs.services.CompanyService;
import ibgdev.tunijobs.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/condidate")
public class CondidateController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping("/companies")
    public String companies(Model model) {
        model.addAttribute("listcompanies", companyService.findAll() );
        return "condidate/companies";
    }
    @GetMapping("/companies/details/{id}")
    public String showCompanyDetails(@PathVariable("id") Long id, Model model) {
        Company company = companyService.findCompanyById(id);
        List<JobOffer> jobOffers = jobOfferService.findJobOffersByEntreprise(company);
        if (company == null) {
            return "redirect:/condidate/companies?error=notfound"; //notfound
        }
        model.addAttribute("id", id);
        model.addAttribute("company", company);
        model.addAttribute("joboffers", jobOffers);
        return "condidate/company-details"; // This is the name of your Thymeleaf template
    }

    @GetMapping("/joboffers")
    public String joboffers(Model model) {
        model.addAttribute("listjoboffers", jobOfferService.findAll() );
        return "condidate/joboffers";
    }

    @GetMapping("/joboffers/details/{id}")
    public String showJobOfferDetails(@PathVariable("id") Long id, Model model) {
        JobOffer jobOffer = jobOfferService.findJobOfferById(id);
        if (jobOffer == null) {
            return "redirect:/condidate/joboffers?error=notfound"; //notfound
        }
        model.addAttribute("id", id);
        model.addAttribute("offer", jobOffer);
        return "condidate/joboffer-details"; // This is the name of your Thymeleaf template
    }
}
