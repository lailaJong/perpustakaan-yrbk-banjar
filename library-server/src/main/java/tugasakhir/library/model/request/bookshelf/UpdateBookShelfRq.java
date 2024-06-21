package tugasakhir.library.model.request.bookshelf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateBookShelfRq {
    @JsonProperty("bookshelf_id")
    private String bookShelfId;

    @JsonProperty("bookshelf_code")
    private String bookShelfCode;
}
