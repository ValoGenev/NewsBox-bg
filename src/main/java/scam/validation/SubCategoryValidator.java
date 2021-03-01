package scam.validation;

import org.apache.commons.lang3.EnumUtils;
import scam.model.Category;
import scam.model.SubCategory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class SubCategoryValidator implements ConstraintValidator<ValidPostSubCategories, Set<String>> {

    @Override
    public boolean isValid(Set<String> subCategories, ConstraintValidatorContext constraintValidatorContext) {
        for (String category: subCategories) {
            if(!EnumUtils.isValidEnum(SubCategory.class,category)){
                return false;
            }
        }
        return true;
    }
}
