package tugasakhir.library.model.request.orderdetail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "String")
    private String orderId;

    @JsonProperty("user_id")
    @NotBlank(message = "User ID is mandatory")
    @Schema(example = "String")
    private String userId;

    @JsonProperty("book_id")
    @NotBlank(message = "Book ID is mandatory")
    @Schema(example = "String")
    private String bookId;

    @JsonProperty("order_date")
    @NotBlank(message = "Order date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(example = "22-04-2000")
    private String orderDate;

    @JsonProperty("taking_date")
    @NotBlank(message = "Taking date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(example = "22-04-2000")
    private String takingDate;

    @JsonProperty("status")
    @NotBlank(message = "Status is mandatory")
    @Schema(example = "String")
    private String status;
}
