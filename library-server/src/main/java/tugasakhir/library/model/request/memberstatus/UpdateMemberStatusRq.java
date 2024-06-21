package tugasakhir.library.model.request.memberstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateMemberStatusRq {
    @JsonProperty("member_status_id")
    private String memberStatusId;

    @JsonProperty("status")
    private String status;
}
