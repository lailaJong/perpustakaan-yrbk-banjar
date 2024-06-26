package tugasakhir.library.model.dto;

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
public class BorrowingTrxOfficer {
    private String borrowingId;
    private String memberName;
    private String bookId;
    private String status;
    private Date borrowingDate;
    private Date returnDate;
}

