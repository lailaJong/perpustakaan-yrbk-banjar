package tugasakhir.library.utils.validation;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StatusValidator implements ConstraintValidator<StatusConstraint, String> {

    private final List<String> allowedStatuses = Arrays.asList("Tersedia", "Dipesan", "Dipinjam");

//    @Override
//    public void initialize(StatusConstraint status) {
//    }

    @Override
    public boolean isValid(String statusField, ConstraintValidatorContext context) {
        if (statusField == null) {
            return false;
        }
        return allowedStatuses.contains(statusField);
    }
}
