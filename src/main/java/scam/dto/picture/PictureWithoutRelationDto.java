package scam.dto.picture;

import java.util.Objects;

public class PictureWithoutRelationDto {

    private static final long serialVersionUID = 1L;

    private String id;

    private String pictureUrl;

    public PictureWithoutRelationDto(String id, String pictureUrl) {
        this.id = id;
        this.pictureUrl = pictureUrl;
    }

    public PictureWithoutRelationDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureWithoutRelationDto that = (PictureWithoutRelationDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
