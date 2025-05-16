package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.*;
import ibgdev.tunijobs.repository.ApplicationRepository;
import ibgdev.tunijobs.repository.UserRepository;
import ibgdev.tunijobs.services.ApplicationService;
import ibgdev.tunijobs.services.CategoryService;
import ibgdev.tunijobs.services.CompanyService;
import ibgdev.tunijobs.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/entreprise")
public class EntrepriseController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobOfferService jobOfferService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;


    @GetMapping("/details")
    public String entrepriseInfos(Model model, Authentication authentication) {
        String username = authentication.getName();
        Company company = companyService.findCompanyByResponsable(userRepository.findUserByEmail(username));
        int nbapp = applicationService.findApplicationsByCompanyId(company).size();
        int nbjobs = jobOfferService.findJobOffersByEntreprise(company).size();
        model.addAttribute("company", company);
        model.addAttribute("nbjobs", nbjobs);
        model.addAttribute("nbapp", nbapp);
        return "entreprise/details";
    }

    @GetMapping("/joboffers")
    public String getMyJobOffers(Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = userRepository.findUserByEmail(userEmail);
        Company company = companyService.findCompanyByResponsable(user);

        List<JobOffer> offers = jobOfferService.findJobOffersByEntreprise(company);
        model.addAttribute("joboffers", offers);

        return "entreprise/joboffers";
    }

    @GetMapping("/joboffers/details/{id}")
    public String showJobOfferDetails(@PathVariable("id") Long id, Model model) {
        JobOffer jobOffer = jobOfferService.findJobOfferById(id);
        if (jobOffer == null) {
            return "redirect:/entreprise/joboffers?error=notfound"; //notfound
        }
        model.addAttribute("id", id);
        model.addAttribute("offer", jobOffer);
        return "entreprise/joboffer-details"; // This is the name of your Thymeleaf template
    }

    @GetMapping("/joboffers/add")
    public String showAddForm(Model model) {
        model.addAttribute("joboffer", new JobOffer());
        model.addAttribute("location", Locations.values());
        model.addAttribute("categories", categoryService.findAll());
        return "entreprise/addjoboffer";
    }

    @PostMapping("/joboffers/addJobffer")
    public String addJobOffer(@ModelAttribute("joboffer") JobOffer jobOffer,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {
        User user = userRepository.findUserByEmail(principal.getName());
        Company company = companyService.findCompanyByResponsable(user);

        jobOffer.setEntreprise(company);
        jobOffer.setDatePublication(new Date());

        jobOfferService.addJobOffer(jobOffer);
        redirectAttributes.addFlashAttribute("message", "You have successfully added a job offer.");
        return "redirect:/entreprise/joboffers";
    }
    @GetMapping("/joboffers/edit")
    public String showUpdateForm(@RequestParam("id") long id, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("joboffer", jobOfferService.findJobOfferById(id));
        model.addAttribute("location", Locations.values());
        return "entreprise/update-Joboffer";
    }

    @PostMapping("/joboffers/update-Joboffer")
    public String updateJobOffer(@ModelAttribute JobOffer jobOffer, RedirectAttributes redirectAttributes) {
        jobOfferService.updateJobOffer(jobOffer.getId(), jobOffer);
        redirectAttributes.addFlashAttribute("message", "Job offer updated successfully");
        return "redirect:/entreprise/joboffers";
    }


    @PostMapping("/deleteOffer")
    public String deleteOffer(@RequestParam("offerId") Long offerId, RedirectAttributes redirectAttributes) {
        jobOfferService.DeleteJobOffer(offerId);
        redirectAttributes.addFlashAttribute("message", "You have successfully deleted a job offer.");
        return "redirect:/entreprise/joboffers";
    }


    @GetMapping("/applications")
    public String getApplications(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findUserByEmail(email);
        Company company = companyService.findCompanyByResponsable(user);
        List<Application> applications = applicationService.findApplicationsByCompanyId(company);
        model.addAttribute("applications", applications);
        return "entreprise/applications"; // Thymeleaf view: resources/templates/entreprise/applications.html
    }

    @PostMapping("/applications/{id}/status")
    public String updateApplicationStatus(
            @PathVariable Long id,
            @RequestParam("status") String status,
            @RequestParam("comment") String comment,
            RedirectAttributes redirectAttributes) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid application Id:" + id));

        application.setStatut(Statut.valueOf(status));
        application.setCommentaire(comment);

        applicationRepository.save(application);

        redirectAttributes.addFlashAttribute("message", "Application status updated successfully.");

        return "redirect:/entreprise/applications";
    }

}
