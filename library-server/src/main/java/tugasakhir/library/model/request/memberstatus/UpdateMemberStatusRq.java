package tugasakhir.library.model.request.memberstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateMemberStatusRq {
    @JsonProperty("member_id")
    @NotBlank(message = "Member ID is mandatory")
    @Schema(example = "String")
    private String memberId;

    @JsonProperty("member_status_id")
    @NotBlank(message = "Member status ID is mandatory")
    @Schema(example = "String")
    private String memberStatusId;

    @JsonProperty("status")
    @NotBlank(message = "Status is mandatory")
    private String status;
}
