package scam.dto.comment;

import org.hibernate.annotations.GenericGenerator;
import scam.dto.post.PostWithoutRelationDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.entity.PostEntity;
import scam.entity.UserEntity;
import scam.validation.CommentWithValidUser;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@CommentWithValidUser
public class CommentAllPropertiesDto {

    @Null(message = "ID SHOULD BE NULL")
    private String id;

    @NotBlank(message = "COMMENT CANNOT BE NULL OR EMPTY")
    private String comment;

    private String authorName;

    private LocalDateTime postedOn;

    private UserWithoutRelationDto user;

    @NotNull(message = "POST CANNOT BE NULL")
    private PostWithoutRelationDto post;

    public CommentAllPropertiesDto(String id, String comment,String authorName,LocalDateTime postedOn, UserWithoutRelationDto user, PostWithoutRelationDto post) {
        this.id = id;
        this.comment = comment;
        this.authorName=authorName;
        this.postedOn=postedOn;
        this.user = user;
        this.post = post;
    }

    public CommentAllPropertiesDto() {
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public PostWithoutRelationDto getPost() {
        return post;
    }

    public void setPost(PostWithoutRelationDto post) {
        this.post = post;
    }
}
