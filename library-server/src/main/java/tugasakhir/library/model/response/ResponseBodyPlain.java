package tugasakhir.library.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseBodyPlain<T> implements IBodyRs {
    private String status;
    private String code;
    private String message;
}
