package tugasakhir.library.model.request.bookshelf;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class BookShelfRq {
    @JsonProperty("bookshelf_id")
    private String bookShelfId;

    @JsonProperty("bookshelf_code")
    private String bookShelfCode;
}

