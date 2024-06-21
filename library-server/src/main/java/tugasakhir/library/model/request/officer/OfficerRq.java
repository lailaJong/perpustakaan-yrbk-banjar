package tugasakhir.library.model.request.officer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class OfficerRq {
    @JsonProperty("officer_id")
    private String officerId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("name")
    private String name;
}

