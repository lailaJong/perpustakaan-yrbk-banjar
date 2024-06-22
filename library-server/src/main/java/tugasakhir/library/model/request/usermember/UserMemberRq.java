package tugasakhir.library.model.request.usermember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;
import tugasakhir.library.utils.validation.UsernameConstraint;

import java.util.Date;

/**
 * @author Putri Mele
 * on 20/06/2024
 */

@Data
@Accessors(chain = true)
@Schema
public class UserMemberRq {

    @JsonProperty("username")
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[a-z0-9_]+$", message = "Username must contain only lowercase letters, numbers, and underscores without spaces")
    @UsernameConstraint
    private String username;

    @JsonProperty("password")
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9._@]+$", message = "Password must contain only letters, numbers, and . _ @ characters without spaces")
    private String password;

    @JsonProperty("name")
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[A-Z0-9\\- ]+$", message = "Name must contain only uppercase letters, numbers, hyphens, and spaces")
    private String name;

    @JsonProperty("gender")
    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only numbers without spaces")
    private String phoneNumber;

    @JsonProperty("place_of_birth")
    @NotBlank(message = "Place of birth is mandatory")
    @Pattern(regexp = "^[A-Z ]+$", message = "Place of birth must contain only uppercase letters and spaces")
    private String placeOfBirth;

    @JsonProperty("date_of_birth")
    @NotBlank(message = "Date Of Birth is mandatory")
    private Date dateOfBirth;

    @JsonProperty("address")
    @NotBlank(message = "Address is mandatory")
    @Pattern(regexp = "^[A-Z0-9., ]+$", message = "Address must contain only uppercase letters, numbers, periods, commas, and spaces")
    private String address;
}
