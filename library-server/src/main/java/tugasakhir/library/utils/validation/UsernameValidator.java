package tugasakhir.library.utils.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.repository.UserRepository;

/**
 * @author Putri Mele
 * on 21/06/2024
 */

@Component
public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(username);
    }
}
