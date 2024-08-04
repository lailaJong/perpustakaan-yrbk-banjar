package tugasakhir.library.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Data
@NoArgsConstructor
@Accessors (chain = true)
public class Member {
    private String memberId;
    private String userId;
    private String memberStatusId;
    private String scoreDetailId;
    private String name;
    private String gender;
    private String phoneNumber;
    private String placeOfBirth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    private String address;
    private int point;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date registrationDate;
}

