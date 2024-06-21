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
public class BorrowingDetail {
    private String borrowingId;
    private String userId;
    private String bookId;
    private Date borrowingDate;
    private Date returnDate;
    private Date actualReturnDate;
    private String status;
}
