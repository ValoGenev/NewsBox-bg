package scam.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import scam.model.Category;
import scam.model.SubCategory;

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

    @Column(name = "url",columnDefinition="text", length=200)
    private String url;

    @ElementCollection(targetClass = SubCategory.class)
    @CollectionTable(name = "post_subcategory",
            joinColumns = @JoinColumn(name = "post_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "subcategory_name")
    private Set<SubCategory> subCategories;

    @Column(name = "description_one",columnDefinition="text", length=10485760)
    private String descriptionOne;

    @Column(name = "description_two",columnDefinition="text", length=10485760)
    private String descriptionTwo;

    @Column(name = "description_three",columnDefinition="text", length=10485760)
    private String descriptionThree;

    @ElementCollection(targetClass = Category.class)
    @CollectionTable(name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "category_name")
    private Set<Category> categories;

    @Column(name="facebook_description",columnDefinition="text", length=10485760)
    private String facebookDescription;

    @Column(name = "youtube_url",columnDefinition="text", length=10485760)
    private String youtubeUrl;

    @Column(name = "author_name",columnDefinition="text", length=10485760)
    private String authorName;

    @Column(name = "postedOn")
    private LocalDateTime postedOn;

    @Column(name = "views")
    private int views;

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

    public String getDescriptionOne() {
        return descriptionOne;
    }

    public void setDescriptionOne(String descriptionOne) {
        this.descriptionOne = descriptionOne;
    }

    public String getDescriptionTwo() {
        return descriptionTwo;
    }

    public void setDescriptionTwo(String descriptionTwo) {
        this.descriptionTwo = descriptionTwo;
    }

    public String getDescriptionThree() {
        return descriptionThree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setDescriptionThree(String descriptionThree) {
        this.descriptionThree = descriptionThree;
    }

    public String getFacebookDescription() {
        return facebookDescription;
    }

    public void setFacebookDescription(String facebookDescription) {
        this.facebookDescription = facebookDescription;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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
