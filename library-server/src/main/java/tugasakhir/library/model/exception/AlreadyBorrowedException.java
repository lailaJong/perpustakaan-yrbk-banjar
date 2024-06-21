package tugasakhir.library.model.exception;

import tugasakhir.library.model.enums.CompletionStatus;
import org.springframework.http.HttpStatus;

public class AlreadyBorrowedException extends CommonException {
    private static final String DISPLAY_MESSAGE = "The Book already borrowed";
    private static final String MESSAGE = "The Book already borrowed by other borrower";
    private static final String CODE = "01";
    private static final String TYPE = "Bad Request";

    public AlreadyBorrowedException() {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, DISPLAY_MESSAGE, MESSAGE, HttpStatus.BAD_REQUEST);
    }
    public AlreadyBorrowedException(String message) {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, DISPLAY_MESSAGE, message, HttpStatus.BAD_REQUEST);
    }

    public AlreadyBorrowedException(String displayMessage, String message) {
        super(CompletionStatus.BUSINESS_ERROR, CODE, TYPE, displayMessage, message, HttpStatus.BAD_REQUEST);
    }
}
