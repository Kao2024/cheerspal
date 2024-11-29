package project.cheerspal.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String postEvent(@ModelAttribute("event") Event event) {
        eventService.createEvent(event); 
        return "redirect:/post_event/event_details?id=" + event.getId(); // Include the event ID in the redirect
    }

    @GetMapping("/event_details")
    public String showEventDetails(@RequestParam("id") Long eventId, Model model) {
        Event event = eventService.getEventById(eventId);
        model.addAttribute("event", event);
        return "event_details";
    }

    @GetMapping("/Index")
    public String showEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "Index";
    }
}