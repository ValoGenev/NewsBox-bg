package scam.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.thumbnail.ThumbNailAllPropertiesDto;
import scam.dto.thumbnail.ThumbNailWithoutPropertiesDto;
import scam.service.IPictureService;
import scam.service.IThumbNailService;

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
@RequestMapping("/config/api/v1/thumbnails")
public class ThumbNailController {

    private static final Logger LOGGER = getLogger(ThumbNailController.class);
    private final IThumbNailService thumbNailService;

    @Autowired
    public ThumbNailController(IThumbNailService thumbNailService) {
        this.thumbNailService = thumbNailService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ThumbNailWithoutPropertiesDto>> getAll() {
        LOGGER.info(GET_ALL_THUMBNAILS_MESSAGE);
        return ok(thumbNailService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ThumbNailAllPropertiesDto> getOne(@PathVariable("id") String id) {
        LOGGER.info(format(FIND_THUMBNAIL_BY_ID_MESSAGE, id));
        return ok(thumbNailService.findOne(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ThumbNailAllPropertiesDto> create(@Valid @RequestBody ThumbNailAllPropertiesDto thumbNail) {
        LOGGER.info(CREATE_THUMBNAIL_MESSAGE);
        return status(CREATED).body(thumbNailService.create(thumbNail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        LOGGER.info(format(DELETE_THUMBNAIL_BY_ID_MESSAGE, id));
        thumbNailService.delete(id);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ThumbNailAllPropertiesDto> update(@Valid @RequestBody ThumbNailAllPropertiesDto thumbNail, @PathVariable("id") String id) {
        LOGGER.info(format(UPDATE_THUMBNAIL_BY_ID_MESSAGE, id));
        return ok(thumbNailService.update(thumbNail, id));
    }
}
