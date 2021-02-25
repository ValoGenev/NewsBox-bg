package scam.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = CommentWithValidUserValidator.class)
@Documented
public @interface CommentWithValidUser {

    String message() default "not a valid username";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
