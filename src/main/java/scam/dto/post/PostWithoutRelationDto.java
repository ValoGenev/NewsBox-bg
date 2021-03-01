package scam.dto.post;

import scam.model.Category;
import scam.model.SubCategory;

import javax.persistence.Column;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Set;

public class PostWithoutRelationDto {

    private String id;

    private String title;

    private String descriptionOne;

    private int views;

    private String descriptionTwo;

    private String descriptionThree;

    private String url;

    private String facebookDescription;

    private String youtubeUrl;

    private String authorName;

    private Set<String> categories;

    private Set<String> subCategories;

    private LocalDateTime postedOn;

    public PostWithoutRelationDto(
            String id,
            String title,
            String descriptionOne,
            String descriptionTwo,
            String descriptionThree,
            Set<String> categories,
            String facebookDescription,
            Set<String> subCategories,
            String url,
            int views,
            String youtubeUrl,
            String authorName,
            LocalDateTime postedOn
    ) {
        this.id = id;
        this.title = title;
        this.descriptionOne=descriptionOne;
        this.descriptionTwo=descriptionTwo;
        this.descriptionThree=descriptionThree;
        this.subCategories=subCategories;
        this.facebookDescription=facebookDescription;
        this.categories=categories;
        this.url=url;
        this.views=views;
        this.youtubeUrl=youtubeUrl;
        this.authorName=authorName;
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

    public Set<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<String> subCategories) {
        this.subCategories = subCategories;
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

    public String getUrl() {
        return url;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public void setUrl(String url) {
        this.url = url;
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
