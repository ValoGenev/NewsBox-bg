package scam.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD,PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = SubCategoryValidator.class)
@Documented
public @interface ValidPostSubCategories {

    String message() default "NOT A VALID SUB CATEGORY. "+
            "Values accepted for Enum class: [//politic\n" +
            "    BULGARIA,\n" +
            "    WORLD,\n" +
            "\n" +
            "    WOMAN,\n" +
            "    MAN,\n" +
            "    FITNESS,\n" +
            "    EXERCISES,\n" +
            "    DRUGS,\n" +
            "\n" +
            "    FOOTBALL,\n" +
            "    TENNIS,\n" +
            "    FORMULA,\n" +
            "    VOLLEYBALL,\n" +
            "    BASKETBALL,\n" +
            "    SKI,\n" +
            "    GAMBLING,\n" +
            "\n" +
            "    FOOD,\n" +
            "    MUSIC,\n" +
            "    VEGAN,\n" +
            "    HISTORY,\n" +
            "    MOVIES,\n" +
            "    HOLIDAYS,\n" +
            "    BOOKS,\n" +
            "    FASHION,\n" +
            "    NATURE,\n" +
            "    TRAFFIC,\n" +
            "    CRASHES,\n" +
            "\n" +
            "    HARDWARE,\n" +
            "    GAMES,\n" +
            "    HOME,\n" +
            "    GIFTS,\n" +
            "    SMART_PHONE,\n" +
            "    CAMERAS,\n" +
            "    CONSOLES,\n" +
            "    GARDEN,\n" +
            "    SKI_REVIEW,\n" +
            "    COSMETIC,\n" +
            "    SCHOOL";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
