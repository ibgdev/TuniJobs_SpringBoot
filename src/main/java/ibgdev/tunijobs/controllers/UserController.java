package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.User;
import ibgdev.tunijobs.repository.UserRepository;
import ibgdev.tunijobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/retrieve-all-users")
    public String getAllUsers(Model model) {
        model.addAttribute("listuser", userRepository.findAll());
        return "user/listusers";
    }

    @GetMapping("/delete")
    public  String deleteUser(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        userService.DeleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        return "redirect:/admin/user/retrieve-all-users";
    }

}
