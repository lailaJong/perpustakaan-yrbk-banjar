package tugasakhir.library.utils.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Putri Mele
 * on 24/06/2024
 */

@Constraint(validatedBy = ValidNameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "Invalid publisher name format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

