package project.cheerspal.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.cheerspal.Contact;
import project.cheerspal.service.ContactService;

/**
 *
 * @author Kiki
 */

@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;
     
    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }
    
    public String handleContactForm(@Valid @ModelAttribute Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return "contact"; 
        }
        contactService.saveContact(contact);
        return "redirect:/";
    }
    
    @PostMapping("/contact")
    public String handleContactForm(@Valid @ModelAttribute Contact contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "contact";
        }
        contactService.saveContact(contact);
        
        return "redirect:/contact_success";
    }
    @GetMapping("/contact_success")
    public String contactSuccess() {
        return "contact_success";
    }
}

