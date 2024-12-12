package project.cheerspal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOrderByDateAsc();
    List<Event> findAllByReportedTrue();
 
}