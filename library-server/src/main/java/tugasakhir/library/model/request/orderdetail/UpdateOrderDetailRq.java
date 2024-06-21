package tugasakhir.library.model.request.orderdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UpdateOrderDetailRq {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("order_date")
    private Date orderDate;

    @JsonProperty("status")
    private String status;
}
