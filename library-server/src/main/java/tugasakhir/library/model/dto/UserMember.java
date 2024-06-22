package tugasakhir.library.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Data
@NoArgsConstructor
@Accessors (chain = true)
public class UserMember {
    private String username;
    private String name;
    private String gender;
    private String phoneNumber;
    private String placeOfBirth;
    private Date dateOfBirth;
    private String address;
    private Date registrationDate;
}
