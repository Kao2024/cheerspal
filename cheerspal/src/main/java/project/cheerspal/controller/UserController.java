package project.cheerspal.controller;

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
@RequestMapping(path = "/register")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
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
}

