package tugasakhir.library.model.request.borrowingdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UpdateBorrowingDetailRq {
    @JsonProperty("borrowing_id")
    @NotBlank(message = "Borrowing ID is mandatory")
    private String borrowingId;

    @JsonProperty("user_id")
    @NotBlank(message = "User ID is mandatory")
    private String userId;

    @JsonProperty("book_id")
    @NotBlank(message = "Book ID is mandatory")
    private String bookId;

    @JsonProperty("borrowing_date")
    @NotNull(message = "Borrowing date is mandatory")
    private Date borrowingDate;

    @JsonProperty("return_date")
    @NotNull(message = "Return date is mandatory")
    private Date returnDate;

    @JsonProperty("actual_return_date")
    private Date actualReturnDate;

    @JsonProperty("status")
    @NotNull(message = "Borrowing date is mandatory")
    private String status;
}
