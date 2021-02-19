package scam.dto.picture;

import scam.dto.post.PostWithoutRelationDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class PictureAllPropertiesDto {

    private String id;

    @NotBlank(message = "PICTURE URL CANNOT BE EMPTY OR NULL")
    private String pictureUrl;

    @NotNull(message = "POST CANNOT BE NULL")
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
