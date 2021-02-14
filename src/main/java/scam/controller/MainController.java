package scam.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scam.dto.post.PostAllPropertiesDto;

import javax.validation.Valid;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;
import static scam.utils.Constants.CREATE_POST_MESSAGE;

@RestController
@RequestMapping("/config/api/v1")
public class MainController {

    private static final Logger LOGGER = getLogger(CommentController.class);

   //TODO
}
