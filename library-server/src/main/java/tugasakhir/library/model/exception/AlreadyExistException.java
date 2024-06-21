package tugasakhir.library.model.exception;

import com.practice.bookmanagement.model.enums.CompletionStatus;
import org.springframework.http.HttpStatus;

public class AlreadyExistException extends CommonException {
    private static final String DISPLAY_MESSAGE = "The Book already exist";
    private static final String MESSAGE = "The Book already exist in catalog";
    private static final String CODE = "02";
    private static final String TYPE = "Bad Request";

    public AlreadyExistException() {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, DISPLAY_MESSAGE, MESSAGE, HttpStatus.BAD_REQUEST);
    }
    public AlreadyExistException(String message) {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, DISPLAY_MESSAGE, message, HttpStatus.BAD_REQUEST);
    }

    public AlreadyExistException(String displayMessage, String message) {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, displayMessage, message, HttpStatus.BAD_REQUEST);
    }
}
