package tugasakhir.library.model.exception;

import tugasakhir.library.model.enums.CompletionStatus;
import tugasakhir.library.model.schema.ApiFault;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommonException extends Exception {
    private final CompletionStatus completionStatus;
    private final String code;
    private final String type;
    private final String displayMessage;
    private final HttpStatus httpStatus;

    public CommonException(CompletionStatus completionStatus, String code, String type, String displayMessage, String message, HttpStatus httpStatus) {
        super(message);
        this.completionStatus = completionStatus;
        this.code = code;
        this.type = type;
        this.displayMessage = displayMessage;
        this.httpStatus = httpStatus;
    }

    public CommonException(Exception exception) {
        super(exception.getMessage());
        this.completionStatus = CompletionStatus.SYSTEM_ERROR;
        this.code = "99";
        this.type = exception.getClass().getSimpleName();
        this.displayMessage = "Internal Server Error";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiFault getApiFault() {
        return new ApiFault()
                .setStatus(completionStatus)
                .setCode(code)
                .setType(type)
                .setMessage(super.getMessage())
                .setDetail(type + ":" + super.getMessage());
    }
}
