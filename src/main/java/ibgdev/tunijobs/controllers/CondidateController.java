package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.*;
import ibgdev.tunijobs.repository.ApplicationRepository;
import ibgdev.tunijobs.repository.UserRepository;
import ibgdev.tunijobs.services.ApplicationService;
import ibgdev.tunijobs.services.CompanyService;
import ibgdev.tunijobs.services.JobOfferService;
import ibgdev.tunijobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/condidate")
public class CondidateController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationService applicationService;

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

    @GetMapping("/application/{offerId}")
    public String showApplicationForm(@PathVariable("offerId") Long offerId, Model model) {
        model.addAttribute("jobOffer", jobOfferService.findJobOfferById(offerId));
        return "condidate/application_form"; // your form page
    }

    @PostMapping("/application/{offerId}")
    public String handleApplicationSubmission(@PathVariable("offerId") Long offerId,
                                              @RequestParam("cvFile") MultipartFile cvFile,
                                              @RequestParam("lettreMotivationFile") MultipartFile lettreMotivationFile,
                                              RedirectAttributes redirectAttributes,
                                              Principal principal) throws IOException {
        User user = userRepository.findUserByEmail(principal.getName());
        JobOffer jobOffer = jobOfferService.findJobOfferById(offerId);

        Optional<Application> existingApp = applicationRepository.findByUserAndJobOffer(user, jobOffer);
        if (existingApp.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Vous avez déjà postulé à cette offre.");
            return "redirect:/condidate/joboffers/details/" + offerId;
        }
        // Validate files
        if (cvFile.isEmpty() || lettreMotivationFile.isEmpty() ||
                !cvFile.getOriginalFilename().endsWith(".pdf") ||
                !lettreMotivationFile.getOriginalFilename().endsWith(".pdf")) {
            return "redirect:/condidate/application/" + offerId + "?error";
        }

        // Define upload path
        String uploadDir = "src/main/resources/static/uploads/applications";

        Files.createDirectories(Paths.get(uploadDir));

        // Save files
        String cvFileName = System.currentTimeMillis() + "_cv_" + cvFile.getOriginalFilename();
        String lettreFileName = System.currentTimeMillis() + "_lettre_" + lettreMotivationFile.getOriginalFilename();

        Path cvPath = Paths.get(uploadDir + cvFileName);
        Path lettrePath = Paths.get(uploadDir + lettreFileName);

        Files.copy(cvFile.getInputStream(), cvPath, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(lettreMotivationFile.getInputStream(), lettrePath, StandardCopyOption.REPLACE_EXISTING);

        // Create Application
        Application application = new Application();
        application.setCv(cvFileName);
        application.setLettreMotivation(lettreFileName);
        application.setCreatedAt(new Date());
        application.setStatut(Statut.PENDING); // example
        application.setJobOffer(jobOfferService.findJobOfferById(offerId));
        application.setUser(userRepository.findUserByEmail(principal.getName())); // adjust as needed

        redirectAttributes.addFlashAttribute("message", "Condidate submitted");
        applicationRepository.save(application);


        return "redirect:/condidate/joboffers/details/" + offerId;
    }

    @GetMapping("/applications")
    public String showApplications(Model model, Principal principal) {
        User user = userRepository.findUserByEmail(principal.getName());
        model.addAttribute("listapplications", applicationService.findApplicationsByUser(user));
        return "condidate/applications";
    }


}
