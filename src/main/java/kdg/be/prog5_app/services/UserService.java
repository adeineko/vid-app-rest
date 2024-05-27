package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.User;
import kdg.be.prog5_app.domain.UserRole;
import kdg.be.prog5_app.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User addUser(String username, String password, UserRole role) {
        User user = new User(username, password, role);
        return userRepository.save(user);
    }
}
