package kdg.be.prog5_app.controllers.api;

import jakarta.validation.Valid;
import kdg.be.prog5_app.controllers.api.dto.UserDto;
import kdg.be.prog5_app.domain.UserRole;
import kdg.be.prog5_app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
public class UsersController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsersController(UserService userService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("{id}")
    ResponseEntity<UserDto> getOneUser(@PathVariable("id") long userId) {
        var user = userService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    @PostMapping
    ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setRole(UserRole.USER);
        var createdUser = userService.addUser(
                userDto.getUsername(),
                encryptedPassword,
                userDto.getRole()
        );
        return new ResponseEntity<>(
                modelMapper.map(createdUser, UserDto.class),
                HttpStatus.CREATED
        );
    }
}
