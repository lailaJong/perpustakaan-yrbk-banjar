package tugasakhir.library.model.request.bookstock;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateBookStockRq {
    @JsonProperty("book_stock_id")
    @NotBlank(message = "Book stock ID is mandatory")
    private String bookStockId;

    @JsonProperty("book_id")
    @NotBlank(message = "Book ID is mandatory")
    private String bookId;

    @JsonProperty("stock")
    @Min(value = 1, message = "Stock must be at least 1")
    @Max(value = 1000, message = "Stock must be at most 1000")
    private int stock;
}
