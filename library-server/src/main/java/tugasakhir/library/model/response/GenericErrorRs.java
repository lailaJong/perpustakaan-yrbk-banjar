package tugasakhir.library.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GenericErrorRs implements IBodyRs {
    private String status;
    private String code;
    private String message;
    private String reason;
}
