package project.cheerspal.service;

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

    public Event createEvent(Event event) {
        return eventRepository.save(event); 
    }

    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found")); 
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        if (eventRepository.existsById(id)) {
            updatedEvent.setId(id);
            return eventRepository.save(updatedEvent);
        }
        throw new RuntimeException("Event not found");
    }

    public void addAttendee(Long eventId, Integer userId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!event.getAttendees().contains(user)) {
            event.getAttendees().add(user);
        }

        if (!user.getEvents().contains(event)) {
            user.getEvents().add(event);
        }

        eventRepository.save(event);
    }
}
