package scam.dto.post;

import scam.dto.comment.CommentWithoutRelationDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.thumbnail.ThumbNailWithoutPropertiesDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.model.Category;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Set;

public class PostAllPropertiesDto {

    @Null(message = "ID SHOULD BE NULL")
    private String id;

    @NotBlank(message = "TITLE CANNOT BE EMPTY OR NULL")
    private String title;

    @NotBlank(message = "DESCRIPTION CANNOT BE EMPTY OR NULL")
    private String description;

    @NotNull(message = "CATEGORY CANNOT BE EMPTY OR NULL")
    private Category category;

    private String facebookDescription;

    private String youtubeUrl;

    private String authorName;

    private LocalDateTime postedOn;

    @NotNull(message = "THUMBNAIL PROPERTY CANNOT BE NULL")
    private ThumbNailWithoutPropertiesDto thumbNailPic;

    private Set<PictureWithoutRelationDto> pictures;

    @NotNull(message = "USER PROPERTY CANNOT BE NULL")
    private UserWithoutRelationDto user;

    private Set<CommentWithoutRelationDto> comments;

    public PostAllPropertiesDto(
            String id,
            String title,
            String description,
            Category category,
            String facebookDescription,
            String youtubeUrl,
            String authorName,
            LocalDateTime postedOn,
            ThumbNailWithoutPropertiesDto thumbNailPic,
            Set<PictureWithoutRelationDto> pictures,
            UserWithoutRelationDto user,
            Set<CommentWithoutRelationDto> comments
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.facebookDescription=facebookDescription;
        this.youtubeUrl=youtubeUrl;
        this.authorName=authorName;
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
