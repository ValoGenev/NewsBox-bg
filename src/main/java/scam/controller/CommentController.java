package scam.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scam.dto.comment.CommentAllPropertiesDto;
import scam.dto.comment.CommentWithoutRelationDto;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.service.ICommentService;
import scam.service.IPictureService;

import javax.validation.Valid;

import java.util.Set;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static scam.utils.Constants.*;

@RestController
@RequestMapping("/config/api/v1/comments")
public class CommentController {

    private static final Logger LOGGER = getLogger(CommentController.class);
    private final ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CommentWithoutRelationDto>> getAll() {
        LOGGER.info(GET_ALL_COMMENTS_MESSAGE);
        return ok(commentService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentAllPropertiesDto> getOne(@PathVariable("id") String id) {
        LOGGER.info(format(FIND_COMMENT_BY_ID_MESSAGE, id));
        return ok(commentService.findOne(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentAllPropertiesDto> create(@Valid @RequestBody CommentAllPropertiesDto comment) {
        LOGGER.info(CREATE_COMMENT_MESSAGE);
        return status(CREATED).body(commentService.create(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        LOGGER.info(format(DELETE_COMMENT_BY_ID_MESSAGE, id));
        commentService.delete(id);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentAllPropertiesDto> update(@Valid @RequestBody CommentAllPropertiesDto comment, @PathVariable("id") String id) {
        LOGGER.info(format(UPDATE_COMMENT_BY_ID_MESSAGE, id));
        return ok(commentService.update(comment, id));
    }
}
