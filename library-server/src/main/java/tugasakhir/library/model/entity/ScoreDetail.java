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
public class ScoreDetail {
    private String scoreDetailId;
//    private int point;
    private int extraBorrowTime;
    private int extraBooksQuota;
}
