package tugasakhir.library.model.request.bookshelf;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateBookShelfRq {
    @JsonProperty("bookshelf_id")
    @NotBlank(message = "Bookshelf ID is mandatory")
    private String bookShelfId;

    @JsonProperty("bookshelf_code")
    @NotBlank(message = "Bookshelf code is mandatory")
    private String bookShelfCode;
}
