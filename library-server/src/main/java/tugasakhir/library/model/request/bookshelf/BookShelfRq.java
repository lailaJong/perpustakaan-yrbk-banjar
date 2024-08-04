package tugasakhir.library.model.request.bookshelf;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import tugasakhir.library.utils.validation.BookShelfCode;

@Data
@Accessors(chain = true)
@Schema
public class BookShelfRq {
    @JsonProperty("bookshelf_code")
    @NotBlank(message = "Bookshelf code is mandatory")
    @Schema(example = "String")
    @BookShelfCode
    private String bookShelfCode;
}

