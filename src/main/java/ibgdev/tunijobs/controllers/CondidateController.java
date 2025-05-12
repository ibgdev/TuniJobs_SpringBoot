package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.JobOffer;
import ibgdev.tunijobs.services.CompanyService;
import ibgdev.tunijobs.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
