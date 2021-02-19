package scam.dto.comment;

import scam.dto.post.PostWithoutRelationDto;
import scam.dto.user.UserWithoutRelationDto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

public class CommentWithUserDto {


    private String id;

    private String comment;


    private LocalDateTime postedOn;

    private UserWithoutRelationDto user;

    public CommentWithUserDto(String id, String comment, LocalDateTime postedOn ,UserWithoutRelationDto user) {
        this.id = id;
        this.comment = comment;
        this.postedOn=postedOn;
        this.user = user;
    }

    public CommentWithUserDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserWithoutRelationDto getUser() {
        return user;
    }

    public void setUser(UserWithoutRelationDto user) {
        this.user = user;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }
}
