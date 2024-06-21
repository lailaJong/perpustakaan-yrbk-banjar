package tugasakhir.library.model.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class UserRq {
    @Nullable
    @JsonProperty("user_id")
    private String userId;

    @Nullable
    @JsonProperty("role_id")
    private String roleId;

    @NotNull
    @NotBlank
    @JsonProperty("username")
    private String username; //must be uniqe

    @NotNull
    @NotBlank
    @JsonProperty("password")
    private String password;
}

