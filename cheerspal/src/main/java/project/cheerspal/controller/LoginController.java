package project.cheerspal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.cheerspal.User;
import project.cheerspal.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String processLogin(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User existingUser = loginService.validateUserCredentials(user.getEmail(), user.getPassword());

        if (existingUser != null) {
            session.setAttribute("loggedInUser", existingUser);
            session.setAttribute("loggedIn", true);
            model.addAttribute("user", existingUser);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
