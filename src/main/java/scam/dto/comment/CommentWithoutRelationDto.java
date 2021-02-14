package scam.dto.comment;

public class CommentWithoutRelationDto {

    private String id;

    private String comment;

    public CommentWithoutRelationDto(String id, String comment) {
        this.id = id;
        this.comment = comment;
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
}
