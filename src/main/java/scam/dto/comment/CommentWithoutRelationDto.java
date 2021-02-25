package scam.dto.comment;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class CommentWithoutRelationDto {

    private String id;

    private String comment;

    private String authorName;


    private LocalDateTime postedOn;

    public CommentWithoutRelationDto(String id, String comment,String authorName, LocalDateTime postedOn) {
        this.id = id;
        this.authorName=authorName;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }
}
