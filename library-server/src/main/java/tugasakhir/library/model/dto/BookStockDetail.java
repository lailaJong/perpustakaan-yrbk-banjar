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
public class BookStockDetail {
    private String bookStockId;
    private String bookId;
    private String bookTitle;
    private int stock;
}


