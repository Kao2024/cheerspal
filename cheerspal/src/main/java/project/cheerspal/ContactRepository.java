package project.cheerspal;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Kiki
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}