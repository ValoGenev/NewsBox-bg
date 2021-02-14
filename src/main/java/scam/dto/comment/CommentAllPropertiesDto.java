package scam.dto.comment;

import org.hibernate.annotations.GenericGenerator;
import scam.dto.post.PostWithoutRelationDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.entity.PostEntity;
import scam.entity.UserEntity;

import javax.persistence.*;

public class CommentAllPropertiesDto {

    private String id;

    private String comment;

    private UserWithoutRelationDto user;

    private PostWithoutRelationDto post;

    public CommentAllPropertiesDto(String id, String comment, UserWithoutRelationDto user, PostWithoutRelationDto post) {
        this.id = id;
        this.comment = comment;
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

    public void setUser(UserWithoutRelationDto user) {
        this.user = user;
    }

    public PostWithoutRelationDto getPost() {
        return post;
    }

    public void setPost(PostWithoutRelationDto post) {
        this.post = post;
    }
}
