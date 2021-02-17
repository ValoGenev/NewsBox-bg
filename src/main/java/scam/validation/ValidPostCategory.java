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
@Constraint(validatedBy = PostCategoryValidator.class)
@Documented
public @interface ValidPostCategory {

    String message() default "NOT A VALID CATEGORY. "+
            "Values accepted for Enum class: [HOROSCOPE, POLITIC, ART, SEX, BULGARIA, CORONA_VIRUS, HEALTH, DRUGS, SECRETS, WORLD]";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
