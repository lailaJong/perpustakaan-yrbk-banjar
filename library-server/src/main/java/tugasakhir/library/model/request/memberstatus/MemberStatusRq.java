package tugasakhir.library.model.request.memberstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class MemberStatusRq {
    @JsonProperty("member_status_id")
    private String memberStatusId;

    @JsonProperty("status")
    private String status;
}

