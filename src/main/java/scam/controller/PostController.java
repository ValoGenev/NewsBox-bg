package scam.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scam.dto.comment.CommentAllPropertiesDto;
import scam.dto.post.PostAllPropertiesDto;
import scam.model.Category;
import scam.service.IPostService;
import scam.validation.ValidCategoryParam;

import javax.validation.Valid;

import java.util.Optional;
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
@Validated
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

    @GetMapping(produces = APPLICATION_JSON_VALUE,params = "category")
    public ResponseEntity<Set<PostAllPropertiesDto>> findAllWithSpecificCategory(@RequestParam("category") @ValidCategoryParam  String category) {
        LOGGER.info(format(GET_ALL_POSTS_WITH_CATEGORY_MESSAGE, category));
        return ok(postService.findAllWithCategory(Category.valueOf(category)));
    }

    @GetMapping(value = "/heading",produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PostAllPropertiesDto>> getAllFromTwoDays() {
        LOGGER.info(GET_HEADING_POSTS);
        return ok(postService.getHeadingPosts());
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

    @GetMapping(value = "/{id}/comments",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CommentAllPropertiesDto>> getPostComments(@PathVariable("id") String id) {
        LOGGER.info(format(GET_POST_COMMENTS_MESSAGE, id));
        return ok(postService.getPostComments(id));
    }

    @GetMapping(value = "/random",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PostAllPropertiesDto>> getRandomPosts() {
        LOGGER.info(GET_RANDOM_POSTS_MESSAGE);
        return ok(postService.getRandomPosts());
    }

    @PatchMapping(value = "/{id}/incrementView",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PostAllPropertiesDto> incrementViews(@PathVariable("id") String id) {
        LOGGER.info(format(INCREMENT_VIEWS_ON_POST_MESSAGE,id));
        return ok(postService.incrementView(id));
    }


    @PostMapping(value = "/saveAll",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PostAllPropertiesDto>> create(@Valid @RequestBody Set<PostAllPropertiesDto> posts) {
        LOGGER.info("CREATING POSTS");
        return status(CREATED).body(postService.createMultiplePosts(posts));
    }

    @GetMapping(value = "/test", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PostAllPropertiesDto>> findPageable(@RequestParam("title") Optional<String> title,@RequestParam("page")Optional<Integer> pageNumber,@RequestParam("sortBy") Optional<String> sortBy) {
        LOGGER.info("TESTING LIITLE API OFC");

        return ResponseEntity.ok(postService.findWithPageable(title.orElse("_"),PageRequest.of(pageNumber.orElse(0),20, Sort.Direction.ASC,sortBy.orElse("postedOn"))));
    }





}
