package project.cheerspal.controller;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.cheerspal.Event;
import project.cheerspal.service.EventService;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.cheerspal.User;

@Controller
@RequestMapping("/post_event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public String showPostEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "post_event";
    }

    @PostMapping
    public String postEvent(@ModelAttribute Event event, @RequestParam Integer userId, @RequestParam String city) {
        event.setCity(city);
        eventService.createEvent(event, userId);
        return "redirect:/post_event/event_details?id=" + event.getId();
    }
    
    @GetMapping("/event_details")
    public String showEventDetails(@RequestParam("id") Long eventId, HttpSession session, Model model) {
        Event event = eventService.getEventById(eventId);
        List<User> participants = event.getParticipants();
        if (participants == null) {
            participants = new ArrayList<>();
        }
        model.addAttribute("event", event);
        model.addAttribute("participants", participants);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("userId", loggedInUser.getId());
        }
        return "event_details";
    }   

    @GetMapping("/Index")
    public String showEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "Index";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/admin";
    }
    
    @PostMapping("/{id}/join")
    public String joinEvent(@PathVariable("id") Long eventId, @RequestParam(value = "userId", required = false) Integer userId, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        try {
            eventService.addParticipantToEvent(eventId, loggedInUser.getId());
            redirectAttributes.addFlashAttribute("successMessage", "You joined the event successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/post_event/event_details?id=" + eventId;
    }
    
    
    @PostMapping("/{id}/report")
    public String reportEvent(@PathVariable("id") Long eventId, HttpSession session, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        Event event = eventService.getEventById(eventId);
        if (event != null) {
            event.setReported(true);
            eventService.saveEvent(event);
            redirectAttributes.addFlashAttribute("successMessage", "Event reported successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Event not found!");
        }
        return "redirect:/post_event/event_details?id=" + eventId;
    }
}