package tugasakhir.library.utils.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Putri Mele
 * on 24/06/2024
 */

public class BookShelfCodeValidator implements ConstraintValidator<BookShelfCode, String> {

    private static final String BOOKSHELF_CODE_PATTERN = "RAK\\d{1,3}-BRS\\d{1,3}";

    @Override
    public void initialize(BookShelfCode constraintAnnotation) {
        // TODO document why this method is empty
    }

    @Override
    public boolean isValid(String bookShelfCode, ConstraintValidatorContext context) {
        if (bookShelfCode == null) {
            return false;
        }
        return bookShelfCode.matches(BOOKSHELF_CODE_PATTERN);
    }
}

