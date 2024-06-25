package tugasakhir.library.model.request.orderdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UpdateOrderDetailRq {
    @JsonProperty("order_id")
    @NotBlank(message = "Order ID is mandatory")
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

    @JsonProperty("taking_date")
    @NotNull(message = "Taking date is mandatory")
    private Date takingDate;

    @JsonProperty("status")
    @NotBlank(message = "Status is mandatory")
    private String status;
}
