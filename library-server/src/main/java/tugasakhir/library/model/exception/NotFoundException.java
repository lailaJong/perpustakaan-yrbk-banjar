package tugasakhir.library.model.exception;

import tugasakhir.library.model.enums.CompletionStatus;
import org.springframework.http.HttpStatus;

public class NotFoundException extends CommonException {
    private static final String DISPLAY_MESSAGE = "The data is not found";
    private static final String MESSAGE = "Data of the book is not found";
    private static final String CODE = "03";
    private static final String TYPE = "Bad Request";

    public NotFoundException() {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, DISPLAY_MESSAGE, MESSAGE, HttpStatus.BAD_REQUEST);
    }
    public NotFoundException(String message) {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, DISPLAY_MESSAGE, message, HttpStatus.BAD_REQUEST);
    }

    public NotFoundException(String displayMessage, String message) {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, displayMessage, message, HttpStatus.BAD_REQUEST);
    }
}
