package scam.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "avatar_color")
    private String avatarColor;

    @Column(name = "comment",columnDefinition="text", length=10485760)
    private String comment;

    @Column(name = "postedOn")
    private LocalDateTime postedOn;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    private PostEntity post;

    public CommentEntity() {

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
