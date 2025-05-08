package ibgdev.tunijobs.controllers;

import ibgdev.tunijobs.entity.Category;
import ibgdev.tunijobs.entity.User;
import ibgdev.tunijobs.repository.UserRepository;
import ibgdev.tunijobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "user/updateuser";
    }

    @PostMapping("/updateuser")
    public String updateUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user.getId(), user);
        redirectAttributes.addFlashAttribute("message", "User updated successfully");
        return "redirect:/admin/user/retrieve-all-users";
    }

    @GetMapping("/delete")
    public  String deleteUser(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        userService.DeleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        return "redirect:/admin/user/retrieve-all-users";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user/adduser";
    }

    @PostMapping("/addUser")
    public String AddUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("message", "User added successfully");
        return "redirect:/admin/user/retrieve-all-users";
    }

}
