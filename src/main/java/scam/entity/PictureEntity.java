package scam.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "pictures")
public class PictureEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @Column(name = "picture_url",columnDefinition="text", length=10485760)
    private String pictureUrl;

    @ManyToOne(targetEntity = PostEntity.class)
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    private PostEntity post;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
