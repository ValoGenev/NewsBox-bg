package scam.dto.post;

import scam.dto.comment.CommentWithoutRelationDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.thumbnail.ThumbNailWithoutPropertiesDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.model.Category;

import java.time.LocalDateTime;
import java.util.Set;

public class PostAllPropertiesDto {

    private String id;

    private String title;

    private String description;

    private Category category;

    private LocalDateTime postedOn;

    private ThumbNailWithoutPropertiesDto thumbNailPic;

    private Set<PictureWithoutRelationDto> pictures;

    private UserWithoutRelationDto user;

    private Set<CommentWithoutRelationDto> comments;

    public PostAllPropertiesDto(String id, String title, String description, Category category, LocalDateTime postedOn, ThumbNailWithoutPropertiesDto thumbNailPic, Set<PictureWithoutRelationDto> pictures, UserWithoutRelationDto user, Set<CommentWithoutRelationDto> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.postedOn = postedOn;
        this.thumbNailPic = thumbNailPic;
        this.pictures = pictures;
        this.user = user;
        this.comments = comments;
    }

    public PostAllPropertiesDto() {
    }

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

    public ThumbNailWithoutPropertiesDto getThumbNailPic() {
        return thumbNailPic;
    }

    public void setThumbNailPic(ThumbNailWithoutPropertiesDto thumbNailPic) {
        this.thumbNailPic = thumbNailPic;
    }

    public Set<PictureWithoutRelationDto> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureWithoutRelationDto> pictures) {
        this.pictures = pictures;
    }

    public UserWithoutRelationDto getUser() {
        return user;
    }

    public void setUser(UserWithoutRelationDto user) {
        this.user = user;
    }

    public Set<CommentWithoutRelationDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentWithoutRelationDto> comments) {
        this.comments = comments;
    }
}
