package tugasakhir.library.model.schema;

import tugasakhir.library.model.enums.CompletionStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiFault {
    private CompletionStatus status;
    private String code;
    private String type;
    private String message;
    private String detail;
}
