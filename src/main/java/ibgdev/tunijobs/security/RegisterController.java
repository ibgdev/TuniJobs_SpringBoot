package ibgdev.tunijobs.security;

import ibgdev.tunijobs.dto.RegistrationDto;
import ibgdev.tunijobs.entity.Roles;
import ibgdev.tunijobs.entity.User;
import ibgdev.tunijobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationDto dto) {
        if (userRepository.findUserByEmail(dto.getEmail()) != null) {
            return "redirect:/register?error";
        }
        User user = new User();
        user.setNom(dto.getNom());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        System.out.println("Registering: " + dto.getEmail() + ", role: " + dto.getRole());
        user.setRole(Roles.valueOf(dto.getRole()));
        user.setCreatedAt(new Date());

        userRepository.save(user);
        return "redirect:/login?registered";
    }
}
