package tugasakhir.library.model.request.officer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateOfficerRq {
    @JsonProperty("officer_id")
    private String officerId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("name")
    private String name;
}
