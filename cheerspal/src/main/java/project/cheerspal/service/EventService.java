package project.cheerspal.service;

import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.cheerspal.Event;
import project.cheerspal.EventRepository;

import java.util.List;
import project.cheerspal.User;
import project.cheerspal.UserRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WeatherService weatherService;

    public Event createEvent(Event event, Integer userId) {
        User host = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        event.setHost(host);
        String weather = weatherService.getWeather(event.getCity(), event.getDate());
        
        if (weather == null) {
            throw new RuntimeException("Weather service returned null");
        }
        event.setWeather(weather);

        return eventRepository.save(event); 
    }
    
    public List<Event> getAllEvents() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        events.sort(Comparator.comparing(Event::getDate));
        return events;
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found")); 
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public void addParticipantToEvent(Long eventId, Integer userId) {
        Event event = eventRepository.findById(eventId)
               .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepository.findById(userId)
               .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (event.getHost() != null && event.getHost().getId().equals(userId)) {
            throw new RuntimeException("Host cannot join their own event");
        }
        event.addParticipant(user);
        eventRepository.save(event);
   } 
    
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
    
    public List<Event> getAllReportedEvents() {
        return eventRepository.findAllByReportedTrue();
    }
    
    
    
}
