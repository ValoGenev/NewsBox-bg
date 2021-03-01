package scam.validation;

import org.apache.commons.lang3.EnumUtils;
import scam.model.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class CategoryParamValidator implements ConstraintValidator<ValidCategoryParam, String> {


    @Override
    public boolean isValid(String category, ConstraintValidatorContext constraintValidatorContext) {
        return EnumUtils.isValidEnum(Category.class, category);
    }

    @Override
    public void initialize(ValidCategoryParam constraintAnnotation) {

    }
}
