package tugasakhir.library.model.response;

import tugasakhir.library.model.enums.CompletionStatus;
import tugasakhir.library.model.enums.Status;
import tugasakhir.library.model.exception.CommonException;
import tugasakhir.library.model.schema.ApiFault;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseInfo<T> {
    private IBodyRs body;
    private HttpStatusCode httpStatusCode;
    private HttpHeaders httpHeaders;
    private List<ApiFault> faults;

    public void setSuccess() {
        this.httpStatusCode = HttpStatus.OK;
        this.body = new ResponseBodyPlain<T>()
                .setStatus(Status.OK.label)
                .setCode("00")
                .setMessage(CompletionStatus.SUCCESS.label);
    }
//    public void setSuccess(String message) {
//        this.httpStatusCode = HttpStatus.OK;
//        this.body = new ResponseBodyPlain<T>()
//                .setStatus(Status.OK.label)
//                .setCode("00")
//                .setMessage(message);
//    }

    public void setSuccess(T data) {
        this.httpStatusCode = HttpStatus.OK;
        this.body = new ResponseBody<T>()
                .setStatus(Status.OK.label)
                .setCode("00")
                .setMessage(CompletionStatus.SUCCESS.label)
                .setData(data);
    }

    public void setBussinessError() {
        this.httpStatusCode = HttpStatus.BAD_REQUEST;
        this.body = new ResponseBody<T>()
                .setStatus(Status.ERROR.label)
                .setCode("400")
                .setMessage(CompletionStatus.BUSINESS_ERROR.label);
    }

    public void setBussinessError(String message) {
        this.httpStatusCode = HttpStatus.BAD_REQUEST;
        this.body = new ResponseBody<T>()
                .setStatus(Status.ERROR.label)
                .setCode("400")
                .setMessage(message);
    }

    public boolean isError() {
        return faults != null && !faults.isEmpty() && !httpStatusCode.is2xxSuccessful();
    }

    public void setCommonException(CommonException ex) {
        this.httpStatusCode = ex.getHttpStatus();
        String status = ex.getCompletionStatus().equals(CompletionStatus.SYSTEM_ERROR) ? "Error" : "Failed";
        this.body = new GenericErrorRs()
                .setStatus(status)
                .setCode(ex.getCode())
                .setMessage(ex.getDisplayMessage())
                .setReason(ex.getMessage());
    }

    public void handleException(Exception ex) {
        if (ex instanceof CommonException exception) {
            setCommonException(exception);
        } else {
            setCommonException(new CommonException(ex));
        }
    }

    public void addException(CommonException ex) {
        if (this.faults == null) {
            this.faults = new ArrayList<>();
        }
        this.faults.add(ex.getApiFault());
    }
}
