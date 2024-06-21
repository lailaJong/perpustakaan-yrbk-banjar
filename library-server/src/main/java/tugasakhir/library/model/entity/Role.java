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
@Accessors (chain = true)
public class Role {
    private String roleId;
    private String roleName; //default member dan officer, yang ditampilkan di fe hanya untuk member, karna acc officer hanya 1
}
