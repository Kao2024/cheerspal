package project.cheerspal;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOrderByDateAsc();
    List<Event> findAllByReportedTrue();
}