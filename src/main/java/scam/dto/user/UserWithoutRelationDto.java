package scam.dto.user;

public class UserWithoutRelationDto {

    private String id;

    private String username;

    private String avatarColor;

    public UserWithoutRelationDto(String id, String username,String avatarColor) {
        this.id = id;
        this.username = username;
        this.avatarColor=avatarColor;
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

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }
}
