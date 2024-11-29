package project.cheerspal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import project.cheerspal.User;

/**
 *
 * @author Kiki
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    @GetMapping("/**")
    public void addUserToModel(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
        }
    }
}
