package scam.dto.post;

import scam.dto.comment.CommentWithUserDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.thumbnail.ThumbNailWithoutPropertiesDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.validation.ValidPostCategories;

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

    private String descriptionOne;

    private String descriptionTwo;

    private String descriptionThree;

    private String url;

    private String facebookDescription;

    private String youtubeUrl;

    private String authorName;

    @ValidPostCategories
    private Set<String> categories;

    private LocalDateTime postedOn;

    private int views;

    @NotNull(message = "THUMBNAIL PROPERTY CANNOT BE NULL")
    private ThumbNailWithoutPropertiesDto thumbNailPic;

    private Set<PictureWithoutRelationDto> pictures;

    @NotNull(message = "USER PROPERTY CANNOT BE NULL")
    private UserWithoutRelationDto user;

    private Set<CommentWithUserDto> comments;

    public PostAllPropertiesDto(
            String id,
            String title,
            String facebookDescription,
            String descriptionOne,
            String descriptionTwo,
            String descriptionThree,
            Set<String> categories,
            int views,
            String url,
            String youtubeUrl,
            String authorName,
            LocalDateTime postedOn,
            ThumbNailWithoutPropertiesDto thumbNailPic,
            Set<PictureWithoutRelationDto> pictures,
            UserWithoutRelationDto user,
            Set<CommentWithUserDto> comments
    ) {
        this.id = id;
        this.title = title;
        this.descriptionOne=descriptionOne;
        this.descriptionTwo=descriptionTwo;
        this.descriptionThree=descriptionThree;
        this.url=url;
        this.categories=categories;
        this.views=views;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public void setDescriptionThree(String descriptionThree) {
        this.descriptionThree = descriptionThree;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
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

    public int getViews() {
        return views;
    }



    public void setViews(int views) {
        this.views = views;
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

    public Set<CommentWithUserDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentWithUserDto> comments) {
        this.comments = comments;
    }
}
