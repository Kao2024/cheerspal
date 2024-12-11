
package project.cheerspal;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Kiki
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByEmail(String email);
    Iterable<User> findByRole(String role);
    
}