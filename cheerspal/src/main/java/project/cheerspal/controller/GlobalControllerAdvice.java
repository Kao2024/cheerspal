package project.cheerspal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.cheerspal.User;

/**
 *
 * @author Kiki
 */
@ControllerAdvice
public class GlobalControllerAdvice {

     @ModelAttribute
    public void addUserToModel(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            model.addAttribute("isAdmin", "ADMIN".equals(loggedInUser.getRole()));
        } else {
            model.addAttribute("isAdmin", false);
        }
    }
    
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorDetails", ex.toString());
        return "error";
    }
}
