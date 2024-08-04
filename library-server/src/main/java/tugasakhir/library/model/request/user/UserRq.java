package tugasakhir.library.model.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;
import tugasakhir.library.utils.validation.UsernameConstraint;

@Data
@Accessors(chain = true)
@Schema
public class UserRq {
    @JsonProperty("username")
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[a-z0-9_]+$", message = "Username must contain only lowercase letters, numbers, and underscores without spaces")
    @UsernameConstraint
    private String username;

    @JsonProperty("password")
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "\\S+", message = "Password cannot contain spaces")
    private String password;
}

