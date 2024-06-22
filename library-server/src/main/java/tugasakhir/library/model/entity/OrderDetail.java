package tugasakhir.library.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Data
@NoArgsConstructor
@Accessors (chain = true)
public class OrderDetail {
    private String orderId;
    private String userId;
    private String bookId;
    private Date orderDate;
    private Date takingDate;
    private String status;
}
