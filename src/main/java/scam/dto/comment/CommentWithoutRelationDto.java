package scam.dto.comment;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class CommentWithoutRelationDto {

    private String id;

    private String comment;


    private LocalDateTime postedOn;

    public CommentWithoutRelationDto(String id, String comment,LocalDateTime postedOn) {
        this.id = id;
        this.comment = comment;
        this.postedOn=postedOn;
    }

    public CommentWithoutRelationDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }
}
