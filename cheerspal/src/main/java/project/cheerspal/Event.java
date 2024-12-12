package project.cheerspal;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String title;
    private String description;
    private LocalDate date;
    private String city;
    private String weather;
    private boolean reported;
    
    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @ManyToMany(mappedBy = "events")
    private List<User> participants = new ArrayList<>();
    
    private String imageUrl;
    
    @OneToMany(mappedBy = "event")
    private List<Feedback> feedbacks = new ArrayList<>();
    
    public void addFeedback(Feedback feedback) {
        if (this.feedbacks == null) {
            this.feedbacks = new ArrayList<>();
        }
        this.feedbacks.add(feedback);
    }
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void addParticipant(User user) {
        this.participants.add(user);
        user.getEvents().add(this);
    }

    public void removeParticipant(User user) {    
        this.participants.remove(user);
        user.getEvents().remove(this);
    } 
    
    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
