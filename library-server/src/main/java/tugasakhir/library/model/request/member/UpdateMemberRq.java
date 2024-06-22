package tugasakhir.library.model.request.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UpdateMemberRq {
    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("member_status_id")
    private String memberStatusId;

    @JsonProperty("score_detail_id")
    private String scoreDetailId;

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

    @JsonProperty("point")
    private int point;

    @JsonProperty("regristrationDate")
    private Date regristrationDate;
}
