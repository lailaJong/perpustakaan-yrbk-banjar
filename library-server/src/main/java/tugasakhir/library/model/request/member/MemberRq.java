package tugasakhir.library.model.request.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@Schema
public class MemberRq {

    @JsonProperty("name")
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[A-Z0-9\\- ]+$", message = "Name must contain only uppercase letters, numbers, hyphens, and spaces")
    private String name;

    @JsonProperty("gender")
    @NotBlank(message = "Gender is mandatory")
    private String gender;

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

    @JsonProperty("address")
    @NotBlank(message = "Address is mandatory")
    @Pattern(regexp = "^[A-Z0-9., ]+$", message = "Address must contain only uppercase letters, numbers, periods, commas, and spaces")
    private String address;

    @JsonProperty("point")
    @Max(value = 1000, message = "Point must not exceed 1000")
    private int point;

    @JsonProperty("regristrationDate")
    private Date regristrationDate;
}

