package scam.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserLoginRegisterDto;
import scam.dto.user.UserWithoutRelationDto;
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
@RequestMapping("/config/api/v1/users")
public class UserController {

    private static final Logger LOGGER = getLogger(UserController.class);
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserWithoutRelationDto>> getAll() {
        LOGGER.info(GET_ALL_USERS_MESSAGE);
        return ok(userService.findAll());
    }

    @GetMapping(value = "/{username}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAllPropertiesDto> getOne(@PathVariable("username") String username) {
        LOGGER.info(format(FIND_USER_BY_USERNAME_MESSAGE, username));
        return ok(userService.findOne(username));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAllPropertiesDto> create(@Valid @RequestBody UserLoginRegisterDto user) {
        LOGGER.info(format(CREATE_USER_MESSAGE, user.getUsername()));
        return status(CREATED).body(userService.create(user));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable("username") String username) {
        LOGGER.info(format(DELETE_USER_BY_USERNAME_MESSAGE, username));
        userService.delete(username);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{username}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAllPropertiesDto> update(@Valid @RequestBody UserLoginRegisterDto user, @PathVariable("username") String username) {
        LOGGER.info(format(UPDATE_USER_BY_USERNAME_MESSAGE, username));
        return ok(userService.update(user, username));
    }





}
