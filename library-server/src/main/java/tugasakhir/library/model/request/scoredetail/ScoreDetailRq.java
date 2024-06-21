package tugasakhir.library.model.request.scoredetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class ScoreDetailRq {
    //score detail id default, hanya ada 10 dengan schema benefit tertentu
    @JsonProperty("scoreDetailId")
    private String scoreDetailId;

//    @JsonProperty("point")
//    private int point;

    @JsonProperty("extraBorrowTime")
    private int extraBorrowTime;

    @JsonProperty("extraBooksQuota")
    private int extraBooksQuota;
}

