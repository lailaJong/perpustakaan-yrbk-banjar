package tugasakhir.library.model.request.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateRoleRq {
    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("role_name")
    private String roleName;
}
