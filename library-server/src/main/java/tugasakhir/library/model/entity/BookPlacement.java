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
public class BookPlacement {
    private String bookPlacementId;
    private String bookShelfId;
    private String bookId;
}
