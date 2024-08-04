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
@Schema
public class OrderDetailRq {

    @JsonProperty("user_id")
    @NotBlank(message = "User ID is mandatory")
    @Schema(example = "String")
    private String userId;

    @JsonProperty("book_id")
    @NotBlank(message = "Book ID is mandatory")
    @Schema(example = "String")
    private String bookId;
}

