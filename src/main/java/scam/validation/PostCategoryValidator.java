package scam.validation;

import org.apache.commons.lang3.EnumUtils;
import scam.model.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PostCategoryValidator implements ConstraintValidator<ValidPostCategory, Set<String>> {


    @Override
    public void initialize(ValidPostCategory annotation) {
    }

    @Override
    public boolean isValid(Set<String> categories, ConstraintValidatorContext context) {

        for (String category: categories) {
            if(!EnumUtils.isValidEnum(Category.class,category)){
                return false;
            }
        }

        return true;

    }
}