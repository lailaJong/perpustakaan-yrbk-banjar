package tugasakhir.library.model.request.bookstock;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class BookStockRq {
    @JsonProperty("book_stock_id")
    private String bookStockId;

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("stock")
    private int stock;
}

