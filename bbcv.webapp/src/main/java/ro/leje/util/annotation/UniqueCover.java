package ro.leje.util.annotation;

import ro.leje.util.validator.UniqueCoverValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Danut Chindris
 * @since March 26, 2017
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCoverValidator.class)
public @interface UniqueCover {

    String message() default "{error.cover.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
