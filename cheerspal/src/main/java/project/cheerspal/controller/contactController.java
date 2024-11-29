package project.cheerspal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Kiki
 */

@Controller
public class contactController {
    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }
}

