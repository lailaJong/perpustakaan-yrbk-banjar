package tugasakhir.library.model.request.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateRoleRq {
    @JsonProperty("role_id")
    @NotBlank(message = "Role ID is mandatory")
    private String roleId;

    @JsonProperty("role_name")
    @NotBlank(message = "Role name is mandatory")
    private String roleName;
}
