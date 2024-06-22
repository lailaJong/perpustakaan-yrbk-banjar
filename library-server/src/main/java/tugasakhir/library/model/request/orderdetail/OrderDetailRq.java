package tugasakhir.library.model.request.orderdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@Schema
public class OrderDetailRq {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("user_id")
    @NotBlank(message = "User ID is mandatory")
    private String userId;

    @JsonProperty("book_id")
    @NotBlank(message = "Book ID is mandatory")
    private String bookId;

    @JsonProperty("order_date")
    @NotNull(message = "Order date is mandatory")
    private Date orderDate;

    @JsonProperty("status")
    @NotBlank(message = "Status is mandatory")
    private String status;
}

