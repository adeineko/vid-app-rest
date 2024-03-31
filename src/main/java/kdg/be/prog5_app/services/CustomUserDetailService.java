package kdg.be.prog5_app.services;

import kdg.be.prog5_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

//    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //My own USER
        var user = userService.getUserByUserName(username);
        if (user != null) {
            var authorities = new ArrayList<SimpleGrantedAuthority>();
            //Spring's USER
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    authorities);
        }
        throw new UsernameNotFoundException("User" + username + "was not found");
    }
}
