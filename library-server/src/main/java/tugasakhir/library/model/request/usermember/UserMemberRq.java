package tugasakhir.library.model.request.usermember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Putri Mele
 * on 20/06/2024
 */

@Data
@Accessors(chain = true)
@Schema
@NotNull
@NotBlank
public class UserMemberRq {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("place_of_birth")
    private String placeOfBirth;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("address")
    private String address;
}
