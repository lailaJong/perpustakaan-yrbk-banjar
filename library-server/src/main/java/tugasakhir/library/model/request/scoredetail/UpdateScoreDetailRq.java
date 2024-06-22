package tugasakhir.library.model.request.scoredetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateScoreDetailRq {
    @JsonProperty("scoreDetailId")
    @NotBlank(message = "Score Detail ID is mandatory")
    private String scoreDetailId;

    @JsonProperty("extraBorrowTime")
    @Min(value = 0, message = "Extra Borrow Time must be at least 0")
    private int extraBorrowTime;

    @JsonProperty("extraBooksQuota")
    @Min(value = 0, message = "Extra Books Quota must be at least 0")
    private int extraBooksQuota;
}
