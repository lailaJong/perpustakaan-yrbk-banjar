package tugasakhir.library.model.request.usermember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UpdateUserMemberRq {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("name")
    @NotBlank(message = "Name is mandatory")
    @Pattern(
            regexp = "^(?!.*\\d)(?!.*[!@#\\$%\\^&\\*()_\\+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{1,60}$|^[A-Z][a-z]*(?: [A-Z][a-z]*){0,59}$",
            message = "Author name must start with a capital letter, each word starts with a capital letter, must not use numbers or punctuation, and must be at most 60 characters long including spaces"
    )
    private String name;

    @JsonProperty("username")
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[a-z0-9_]+$", message = "Username must contain only lowercase letters, numbers, and underscores without spaces")
    @UsernameConstraint
    private String username;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^08[0-9]+$", message = "Phone number must start with '08' and contain only numbers without spaces")
    private String phoneNumber;

    @JsonProperty("place_of_birth")
    @NotBlank(message = "Place of birth is mandatory")
    @Pattern(regexp = "([A-Z][a-z]+)(\\s[A-Z][a-z]+)*", message = "Place of birth must be alphabetic, start each word with a capital followed by lowercase letters, and contain no special characters or numbers")
    @Size(max = 150, message = "Place of birth must not exceed 150 characters")
    private String placeOfBirth;

    @JsonProperty("date_of_birth")
    @NotBlank(message = "Date Of Birth is mandatory")
    private Date dateOfBirth;

    @JsonProperty("gender")
    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @JsonProperty("address")
    @NotBlank(message = "Address is mandatory")
    @Pattern(regexp = "^[A-Z0-9., ]+$", message = "Address must contain only uppercase letters, numbers, periods, commas, and spaces")
    private String address;
}
