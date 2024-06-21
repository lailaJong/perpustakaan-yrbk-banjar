package tugasakhir.library.model.request.bookstock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateBookStockRq {
    @JsonProperty("book_stock_id")
    private String bookStockId;

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("stock")
    private int stock;
}
