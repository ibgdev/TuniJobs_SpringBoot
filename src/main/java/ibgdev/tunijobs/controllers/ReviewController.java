package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/getReviews")
    public String getReviews(Model model) {
        model.addAttribute("listreview", reviewService.findAll());
        return "review/listreview";
    }

    @GetMapping("/delete")
    public String deleteReview(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        reviewService.DeleteReview(id);
        redirectAttributes.addFlashAttribute("message", "Review deleted");
        return "redirect:/review/getReviews";
    }
}
