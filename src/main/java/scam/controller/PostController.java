package scam.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scam.dto.post.PostAllPropertiesDto;
import scam.dto.post.PostWithoutRelationDto;
import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.service.IPostService;
import scam.service.IUserService;

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
@RequestMapping("/config/api/v1/posts")
public class PostController {

    private static final Logger LOGGER = getLogger(PostController.class);
    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PostAllPropertiesDto>> getAll() {
        LOGGER.info(GET_ALL_POSTS_MESSAGE);
        return ok(postService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PostAllPropertiesDto> getOne(@PathVariable("id") String id) {
        LOGGER.info(format(FIND_POST_BY_ID_MESSAGE, id));
        return ok(postService.findOne(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PostAllPropertiesDto> create(@Valid @RequestBody PostAllPropertiesDto post) {
        LOGGER.info(format(CREATE_POST_MESSAGE, post.getUser().getUsername()));
        return status(CREATED).body(postService.create(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        LOGGER.info(format(DELETE_POST_BY_ID_MESSAGE, id));
        postService.delete(id);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PostAllPropertiesDto> update(@Valid @RequestBody PostAllPropertiesDto post, @PathVariable("id") String id) {
        LOGGER.info(format(UPDATE_POST_BY_ID_MESSAGE, id));
        return ok(postService.update(post, id));
    }
}
