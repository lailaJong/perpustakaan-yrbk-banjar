package tugasakhir.library.model.request.scoredetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateScoreDetailRq {
    @JsonProperty("scoreDetailId")
    private String scoreDetailId;

//    @JsonProperty("point")
//    private int point;

    @JsonProperty("extraBorrowTime")
    private int extraBorrowTime;

    @JsonProperty("extraBooksQuota")
    private int extraBooksQuota;
}
