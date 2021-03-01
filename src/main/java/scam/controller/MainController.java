package scam.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scam.dto.CategoryWithSubCategoryDto;
import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserLoginRegisterDto;
import scam.model.Category;
import scam.service.IUserService;

import javax.validation.Valid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;
import static scam.utils.Constants.*;

@RestController
@RequestMapping("/config/api/v1")
public class MainController {

    private static final Logger LOGGER = getLogger(MainController.class);

    private final IUserService userService;

    @Autowired
    public MainController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login",produces = APPLICATION_JSON_VALUE,consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAllPropertiesDto> login(@Valid @RequestBody UserLoginRegisterDto user) {
        LOGGER.info(format(LOGIN_USER_MESSAGE,user.getUsername()));
        return ResponseEntity.ok(userService.findOne(user.getUsername()));
    }

    @PostMapping(value="/logout")
    public ResponseEntity<Void> logout() {
        LOGGER.info(LOGOUT_USER_MESSAGE);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value="/categories")
    public ResponseEntity<Set<CategoryWithSubCategoryDto>> getCategories() {
        LOGGER.info("GETTING CATEGORIES");

        Set<CategoryWithSubCategoryDto> categories = new HashSet<>();

        Arrays.stream(Category.values()).forEach(c->{
            CategoryWithSubCategoryDto categoryWithSubCategoryDto = new CategoryWithSubCategoryDto(c,c.getSubCategories());
            categories.add(categoryWithSubCategoryDto);
        });
        return ResponseEntity.ok(categories);
    }

}
