package tugasakhir.library.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Data
@NoArgsConstructor
@Accessors (chain = true)
public class UpdateMemberStatusRq {
    private String memberId;
    private String memberStatusId;
}
