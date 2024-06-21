package tugasakhir.library.model.request.borrowingdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@Schema
public class BorrowingDetailRq {
    @JsonProperty("borrowing_id")
    private String borrowingId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("borrowing_date")
    private Date borrowingDate;

    @JsonProperty("return_date")
    private Date returnDate;

    @JsonProperty("actual_return_date")
    private Date actualReturnDate;

    @JsonProperty("status")
    private String status;
}

