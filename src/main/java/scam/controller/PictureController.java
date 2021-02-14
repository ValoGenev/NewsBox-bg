package scam.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.post.PostAllPropertiesDto;
import scam.dto.post.PostWithoutRelationDto;
import scam.service.IPictureService;
import scam.service.IPostService;

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
@RequestMapping("/config/api/v1/pictures")
public class PictureController {

    private static final Logger LOGGER = getLogger(PictureController.class);
    private final IPictureService pictureService;

    @Autowired
    public PictureController(IPictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PictureWithoutRelationDto>> getAll() {
        LOGGER.info(GET_ALL_PIC_MESSAGE);
        return ok(pictureService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PictureAllPropertiesDto> getOne(@PathVariable("id") String id) {
        LOGGER.info(format(FIND_PIC_BY_ID_MESSAGE, id));
        return ok(pictureService.findOne(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PictureAllPropertiesDto> create(@Valid @RequestBody PictureAllPropertiesDto post) {
        LOGGER.info(CREATE_PIC_MESSAGE);
        return status(CREATED).body(pictureService.create(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        LOGGER.info(format(DELETE_PIC_BY_ID_MESSAGE, id));
        pictureService.delete(id);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PictureAllPropertiesDto> update(@Valid @RequestBody PictureAllPropertiesDto post, @PathVariable("id") String id) {
        LOGGER.info(format(UPDATE_POST_BY_ID_MESSAGE, id));
        return ok(pictureService.update(post, id));
    }
}
