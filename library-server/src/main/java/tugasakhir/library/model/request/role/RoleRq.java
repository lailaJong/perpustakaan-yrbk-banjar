package tugasakhir.library.model.request.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class RoleRq {
    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("role_name")
    private String roleName;
}

