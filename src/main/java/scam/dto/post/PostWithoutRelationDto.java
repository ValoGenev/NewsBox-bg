package scam.dto.post;

import scam.model.Category;

import javax.persistence.Column;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

public class PostWithoutRelationDto {

    private String id;

    private String title;

    private String descriptionOne;

    private int views;

    private String descriptionTwo;

    private String descriptionThree;

    private String facebookDescription;

    private String youtubeUrl;

    private String authorName;

    private Category category;

    private LocalDateTime postedOn;

    public PostWithoutRelationDto(
            String id,
            String title,
            String descriptionOne,
            String descriptionTwo,
            String descriptionThree,
            String facebookDescription,
            int views,
            String youtubeUrl,
            String authorName,
            Category category,
            LocalDateTime postedOn
    ) {
        this.id = id;
        this.title = title;
        this.descriptionOne=descriptionOne;
        this.descriptionTwo=descriptionTwo;
        this.descriptionThree=descriptionThree;
        this.facebookDescription=facebookDescription;
        this.views=views;
        this.youtubeUrl=youtubeUrl;
        this.authorName=authorName;
        this.category = category;
        this.postedOn = postedOn;
    }

    public PostWithoutRelationDto() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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
}
