package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.User;
import kdg.be.prog5_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
