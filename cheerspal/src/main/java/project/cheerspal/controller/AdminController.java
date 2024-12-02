package project.cheerspal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.cheerspal.Contact;
import project.cheerspal.Event;
import project.cheerspal.User;
import project.cheerspal.service.ContactService;
import project.cheerspal.service.EventService;
import project.cheerspal.service.UserService;

/**
 *
 * @author Kiki
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private ContactService contactService;
    
    @GetMapping
    public String adminDashboard(Model model) {
        Iterable<User> users = userService.getAllUsersByRole("user");
        model.addAttribute("users", users);
        Iterable<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        Iterable<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contactService.getAllContacts());
        return "admin";
    }
}
