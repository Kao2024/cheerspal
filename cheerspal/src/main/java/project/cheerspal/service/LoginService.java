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
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public User validateUserCredentials(String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return existingUser;
        }
        return null;
    }
}