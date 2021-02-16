package scam.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class UserCreateDto {

    @NotBlank(message = "USERNAME CANNOT BE EMPTY OR NULL")
    private String username;

    @NotBlank(message = "PASSWORD CANNOT BE EMPTY OR NULL")
    private String password;

    public UserCreateDto(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public UserCreateDto() {
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
}
