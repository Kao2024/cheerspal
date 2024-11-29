package project.cheerspal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.cheerspal.User;
import project.cheerspal.UserRepository;
import project.cheerspal.service.LoginService;

/**
 *
 * @author Kiki
 */
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
            model.addAttribute("user", existingUser);
            return "login_success";
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }
    }

    @GetMapping("/login_success")
    public String showLoginSuccess(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);
        return "login_success";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}