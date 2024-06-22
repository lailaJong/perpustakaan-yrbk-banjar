package tugasakhir.library.model.request.scoredetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class ScoreDetailRq {
    @JsonProperty("scoreDetailId")
    private String scoreDetailId;

    @JsonProperty("extraBorrowTime")
    @Min(value = 0, message = "Extra Borrow Time must be at least 0")
    private int extraBorrowTime;

    @JsonProperty("extraBooksQuota")
    @Min(value = 0, message = "Extra Books Quota must be at least 0")
    private int extraBooksQuota;
}

