package project.cheerspal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.cheerspal.User;
import project.cheerspal.service.UserService;

/**
 *
 * @author Kiki
 */

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, Model model) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "register_problem";
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists!");
            return "register_problem";
        }

        userService.saveUser(user);
        model.addAttribute("user", user);
        return "register_success";
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/user_details/{id}")
    public String showUserDetails(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "error";
        }
        model.addAttribute("user", user);
        return "user_details";
    }
    
    @GetMapping("/profile/{id}")
    public String showUserProfile(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "error";
        }
        model.addAttribute("user", user);
        return "profile";
    }
    
    @PostMapping("/profile/update/{id}")
    public String updateProfile(@PathVariable Integer id, @ModelAttribute("user") User updatedUser, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || !loggedInUser.getId().equals(id)) {
            return "redirect:/login";
        }

        updatedUser.setId(id);
        userService.updateUser(updatedUser);
        session.setAttribute("loggedInUser", updatedUser);
        model.addAttribute("successMessage", "Profile updated successfully!");
        return "redirect:/user_details/" + id;
    }

}

