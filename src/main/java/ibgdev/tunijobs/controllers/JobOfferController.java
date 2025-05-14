package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/joboffers")
public class JobOfferController {
    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping("/retrieve-all-jobs")
    public String getAllJobs(Model model) {
        model.addAttribute("jobOffers", jobOfferService.findAll());
        return "joboffers/listjobs";
    }

    @GetMapping("/delete")
    public String deleteJob(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        jobOfferService.DeleteJobOffer(id);
        redirectAttributes.addFlashAttribute("message", "Job offer deleted");
        return "redirect:/admin/joboffers/retrieve-all-jobs";
    }
}
