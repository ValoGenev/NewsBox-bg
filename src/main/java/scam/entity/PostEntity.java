package scam.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import scam.model.Category;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "posts")
public class PostEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @Column(name = "title",columnDefinition="text", length=10485760)
    private String title;

    @Column(name = "description",columnDefinition="text", length=10485760)
    private String description;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "postedOn")
    private LocalDateTime postedOn;

    @OneToOne(targetEntity = ThumbNailEntity.class)
    @JoinColumn(name = "thumb_nail_pic_id",referencedColumnName = "id")
    private ThumbNailEntity thumbNailPic;

    @OneToMany(targetEntity = PictureEntity.class,mappedBy = "post")
    private Set<PictureEntity> pictures;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(targetEntity = CommentEntity.class,mappedBy = "post")
    private Set<CommentEntity> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public ThumbNailEntity getThumbNailPic() {
        return thumbNailPic;
    }

    public void setThumbNailPic(ThumbNailEntity thumbNailPic) {
        this.thumbNailPic = thumbNailPic;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public PostEntity() {
    }




}
