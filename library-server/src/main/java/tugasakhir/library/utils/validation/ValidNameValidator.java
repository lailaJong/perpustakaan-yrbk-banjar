package tugasakhir.library.utils.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Putri Mele
 * on 24/06/2024
 */

public class ValidNameValidator implements ConstraintValidator<ValidName, String> {

    private static final String NAME_PATTERN = "^[A-Z][a-z]*(?: [A-Z][a-z]*)*$";

    @Override
    public void initialize(ValidName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String publisherName, ConstraintValidatorContext context) {
        if (publisherName == null) {
            return false;
        }
        return publisherName.matches(NAME_PATTERN);
    }
}

