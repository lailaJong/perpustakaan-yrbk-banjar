package tugasakhir.library.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Putri Mele
 * on 09/06/2024
 */
@Data
@NoArgsConstructor
@NotNull
@Accessors(chain = true)
public class TopBorrowedBook {
        private String bookTitle;
        private long totalBorrowings;
}
