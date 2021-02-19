package scam.entity;

import org.hibernate.annotations.GenericGenerator;
import scam.model.UserRole;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @Column(name="username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar_color")
    private String avatarColor;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user",targetEntity = PostEntity.class,fetch = FetchType.EAGER)
    private Set<PostEntity> posts;

    @OneToMany(mappedBy = "user",targetEntity = CommentEntity.class,fetch = FetchType.EAGER)
    private Set<CommentEntity> comments;


    public UserEntity() {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }

    public Set<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }
}
