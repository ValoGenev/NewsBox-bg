package scam.validation;

import org.apache.commons.lang3.EnumUtils;
import scam.model.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PostCategoryValidator implements ConstraintValidator<ValidPostCategory, String> {


    @Override
    public void initialize(ValidPostCategory annotation) {
    }

    @Override
    public boolean isValid(String category, ConstraintValidatorContext context) {

        return EnumUtils.isValidEnum(Category.class,category);
    }
}