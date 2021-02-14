package scam.dto.thumbnail;

import scam.dto.post.PostWithoutRelationDto;

public class ThumbNailWithoutPropertiesDto {

    private String id;

    private String pictureUrl;

    public ThumbNailWithoutPropertiesDto(String id, String pictureUrl) {
        this.id = id;
        this.pictureUrl = pictureUrl;
    }

    public ThumbNailWithoutPropertiesDto() {
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
}
