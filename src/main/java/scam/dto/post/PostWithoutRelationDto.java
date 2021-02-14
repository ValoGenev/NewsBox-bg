package scam.dto.post;

import scam.model.Category;

import java.time.LocalDateTime;

public class PostWithoutRelationDto {

    private String id;

    private String title;

    private String description;

    private Category category;

    private LocalDateTime postedOn;

    public PostWithoutRelationDto(String id, String title, String description, Category category, LocalDateTime postedOn) {
        this.id = id;
        this.title = title;
        this.description = description;
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


}
