package tugasakhir.library.model.request.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@Schema
public class MemberRq {
    @Nullable
    @JsonProperty("member_id")
    private String memberId;

    @Nullable
    @JsonProperty("user_id")
    private String userId;

    @Nullable
    @JsonProperty("member_status_id")
    private String memberStatusId;

    @Nullable
    @JsonProperty("score_detail_id")
    private String scoreDetailId;

    @NotBlank
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotBlank
    @NotNull
    @JsonProperty("gender")
    private String gender;

    @NotBlank
    @NotNull
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank
    @NotNull
    @JsonProperty("place_of_birth")
    private String placeOfBirth;

    @NotBlank
    @NotNull
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @NotBlank
    @NotNull
    @JsonProperty("address")
    private String address;

    @NotBlank
    @NotNull
    @JsonProperty("point")
    private int point;
}

