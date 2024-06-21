package tugasakhir.library.model.request.member;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("point")
    private int point;
}
