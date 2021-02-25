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

    private String authorName;

    private LocalDateTime postedOn;

    private UserWithoutRelationDto user;

    public CommentWithUserDto(String id, String comment, String authorName,LocalDateTime postedOn ,UserWithoutRelationDto user) {
        this.id = id;
        this.comment = comment;
        this.authorName=authorName;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
