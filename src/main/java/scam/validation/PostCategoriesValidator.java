package scam.validation;

import org.apache.commons.lang3.EnumUtils;
import scam.model.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class PostCategoriesValidator implements ConstraintValidator<ValidPostCategories, Set<String>> {


    @Override
    public void initialize(ValidPostCategories annotation) {
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