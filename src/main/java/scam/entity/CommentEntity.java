package scam.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Column(name = "comment",columnDefinition="text", length=10485760)
    private String comment;

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

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
