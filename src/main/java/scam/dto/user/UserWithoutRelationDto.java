package scam.dto.user;

public class UserWithoutRelationDto {

    private String id;

    private String username;

    public UserWithoutRelationDto(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserWithoutRelationDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
