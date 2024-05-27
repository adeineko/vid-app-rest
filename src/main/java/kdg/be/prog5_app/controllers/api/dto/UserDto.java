package kdg.be.prog5_app.controllers.api.dto;

import kdg.be.prog5_app.domain.UserRole;

public class UserDto {

    private long id;
    private String username;
    private String password;
    private UserRole role;

    public UserDto() {
    }


    public UserDto(long id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = UserRole.USER;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
