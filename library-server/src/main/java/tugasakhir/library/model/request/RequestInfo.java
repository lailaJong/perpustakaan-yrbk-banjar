package tugasakhir.library.model.request;

import tugasakhir.library.model.response.ResponseInfo;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class RequestInfo {
    private String completionStatus;
    private Object errorDetails;
    private Object responseHeaders;
    private Object responsePayload;
    private String statusCode;

    @Builder(builderMethodName = "newBuilder")
    public RequestInfo(String completionStatus, Object errorDetails, Object responseHeaders, Object responsePayload, String statusCode) {
        this.completionStatus = completionStatus;
        this.errorDetails = errorDetails;
        this.responseHeaders = responseHeaders;
        this.responsePayload = responsePayload;
        this.statusCode = statusCode;
    }
    public void setResponse(ResponseInfo<Object> responseInfo) {
        this.completionStatus = "Success";
        if (responseInfo.isError()) {
            this.errorDetails = responseInfo.getFaults();
            if (responseInfo.getHttpStatusCode().is5xxServerError()) {
                this.completionStatus = "SystemError";
            } else {
                this.completionStatus = "BusinessError";
            }
            Set<String> nameSet = new HashSet<>();
            this.errorDetails = responseInfo.getFaults().stream().filter(e -> nameSet.add(e.getDetail())).toList();
        }
        this.responsePayload = responseInfo.getBody();
        this.responseHeaders = responseInfo.getHttpHeaders();
        this.statusCode = String.valueOf(responseInfo.getHttpStatusCode().value());
    }
}
