package tugasakhir.library.model.request.memberstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateMemberStatusRq {
    @JsonProperty("member_status_id")
    @NotBlank(message = "Member status ID is mandatory")
    private String memberStatusId;

    @JsonProperty("status")
    @NotBlank(message = "Status is mandatory")
    private String status;
}
