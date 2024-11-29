package project.cheerspal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.cheerspal.Event;
import project.cheerspal.EventRepository;

/**
 *
 * @author Kiki
 */

@Controller
public class HomeController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping({"/", "/Index"})
    public String indexPage(Model model) {
        List<Event> events = (List<Event>) eventRepository.findAll();
        model.addAttribute("events", events);
        return "Index";
    }
}

