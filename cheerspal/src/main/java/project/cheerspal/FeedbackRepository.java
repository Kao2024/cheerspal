package project.cheerspal;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Kiki
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
}
