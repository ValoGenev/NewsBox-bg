package scam.validation;

import org.apache.commons.lang3.StringUtils;
import scam.dto.comment.CommentAllPropertiesDto;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


public class CommentWithValidUserValidator implements ConstraintValidator<CommentWithValidUser, CommentAllPropertiesDto> {


    @Override
    public void initialize(CommentWithValidUser constraintAnnotation) {

    }

    @Override
    public boolean isValid(CommentAllPropertiesDto s, ConstraintValidatorContext constraintValidatorContext) {

        if(s.getUser()==null){
            return !StringUtils.isBlank(s.getAuthorName());
        }
        return true;
    }
}
