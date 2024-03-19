package kdg.be.prog5_app.security;

import kdg.be.prog5_app.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.getUserByUserName(username);
        if (user != null) {
            var authorities = new ArrayList<SimpleGrantedAuthority>();
            return new CustomUserDetails(
                    user.getUsername(),
                    user.getPassword(),
                    authorities,
                    user.getId());
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }
}
