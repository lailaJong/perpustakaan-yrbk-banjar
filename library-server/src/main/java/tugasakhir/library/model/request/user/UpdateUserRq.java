package tugasakhir.library.model.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateUserRq {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;
}
