package scam.dto.user;

import org.hibernate.annotations.GenericGenerator;
import scam.dto.comment.CommentWithoutRelationDto;
import scam.dto.post.PostWithoutRelationDto;
import scam.entity.CommentEntity;
import scam.entity.PostEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

public class UserAllPropertiesDto {

    private String id;

    private String username;

    private String password;

    private Set<PostWithoutRelationDto> posts;

    private Set<CommentWithoutRelationDto> comments;

    public UserAllPropertiesDto(String id, String username, String password, Set<PostWithoutRelationDto> posts, Set<CommentWithoutRelationDto> comments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.posts = posts;
        this.comments = comments;
    }

    public UserAllPropertiesDto() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PostWithoutRelationDto> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostWithoutRelationDto> posts) {
        this.posts = posts;
    }

    public Set<CommentWithoutRelationDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentWithoutRelationDto> comments) {
        this.comments = comments;
    }
}
