package tugasakhir.library.utils.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author Putri Mele
 * on 21/06/2024
 */
@Constraint(validatedBy =UsernameValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameConstraint {
    String message() default "Username tidak tersedia";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
