package tugasakhir.library.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
public class BorrowRq {
    @JsonProperty("book_id")
    @NotBlank(message = "Book Id must not be blank")
    private String bookId;
    @JsonProperty("borrower")
    @NotBlank(message = "Book Id must not be blank")
    private String borrower;
    @JsonProperty("date_borrowed")
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateBorrowed;
    @JsonProperty("date_returned")
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateReturned;
}
