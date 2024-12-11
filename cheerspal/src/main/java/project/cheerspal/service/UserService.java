package project.cheerspal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.cheerspal.User;
import project.cheerspal.UserRepository;
/**
 *
 * @author Kiki
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Iterable<User> getAllUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
    
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
}
