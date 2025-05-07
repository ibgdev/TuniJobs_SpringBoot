package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.User;
import ibgdev.tunijobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String userprofile(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        model.addAttribute("user", user);
        return "/profile";
    }

    @GetMapping("/become-a-company")
    public String becomeCompany(Model model) {
        return "becomeAcompany";
    }
}
