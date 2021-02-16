package scam.dto.thumbnail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

public class ThumbNailCreateDto {

    @Null(message = "THUMBNAIL ID SHOULD BE NULL")
    private String id;

    @NotBlank(message = "PICTURE URL CANNOT BE NULL OR EMPTY")
    private String pictureUrl;

    public ThumbNailCreateDto(String id, String pictureUrl) {
        this.id = id;
        this.pictureUrl = pictureUrl;
    }

    public ThumbNailCreateDto() {
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
