package tugasakhir.library.utils.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

/**
 * @author Putri Mele
 * on 18/06/2024
 */

@Documented
@Constraint(validatedBy = StatusValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusConstraint{
    String message() default "Invalid status";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
}

