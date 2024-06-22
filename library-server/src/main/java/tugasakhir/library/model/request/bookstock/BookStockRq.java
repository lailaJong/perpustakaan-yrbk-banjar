package tugasakhir.library.model.request.bookstock;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class BookStockRq {
    @Nullable
    @JsonProperty("book_stock_id")
    private String bookStockId;

    @JsonProperty("book_id")
    @NotBlank(message = "Book ID is mandatory")
    private String bookId;

    @JsonProperty("stock")
    @Min(value = 1, message = "Stock must be at least 1")
    private int stock;
}
