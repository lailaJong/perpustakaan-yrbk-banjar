package tugasakhir.library.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Putri Mele
 * on 09/06/2024
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private String userId;
    private String roleId;
    private String username;
    private String password;
}
