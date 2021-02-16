package scam.dto.post;

import scam.model.Category;

import java.time.LocalDateTime;

public class PostWithoutRelationDto {

    private String id;

    private String title;

    private String description;

    private String facebookDescription;

    private String youtubeUrl;

    private String authorName;

    private Category category;

    private LocalDateTime postedOn;

    public PostWithoutRelationDto(
            String id,
            String title,
            String description,
            String facebookDescription,
            String youtubeUrl,
            String authorName,
            Category category,
            LocalDateTime postedOn
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.facebookDescription=facebookDescription;
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
}
