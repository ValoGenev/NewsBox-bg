package scam.dto.thumbnail;

import org.hibernate.annotations.GenericGenerator;
import scam.dto.post.PostWithoutRelationDto;
import scam.entity.PostEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class ThumbNailAllPropertiesDto {

    private String id;

    @NotBlank(message = "PICTURE URL CANNOT BE NULL OR EMPTY")
    private String pictureUrl;

    private PostWithoutRelationDto post;

    public ThumbNailAllPropertiesDto(String id, String pictureUrl, PostWithoutRelationDto post) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.post = post;
    }

    public ThumbNailAllPropertiesDto() {
    }

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

    public PostWithoutRelationDto getPost() {
        return post;
    }

    public void setPost(PostWithoutRelationDto post) {
        this.post = post;
    }
}
