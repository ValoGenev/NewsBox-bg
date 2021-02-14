package scam.dto.picture;

import scam.dto.post.PostWithoutRelationDto;

import java.util.Set;

public class PictureAllPropertiesDto {

    private String id;

    private String pictureUrl;

    private PostWithoutRelationDto post;

    public PictureAllPropertiesDto(String id, String pictureUrl, PostWithoutRelationDto post) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.post = post;
    }

    public PictureAllPropertiesDto() {
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
