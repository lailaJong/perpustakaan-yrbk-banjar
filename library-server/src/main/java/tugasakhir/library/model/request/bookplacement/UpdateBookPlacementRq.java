package tugasakhir.library.model.request.bookplacement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateBookPlacementRq {
    @JsonProperty("book_placement_id")
    private String bookPlacementId;

    @JsonProperty("bookshelf_id")
    private String bookShelfId;

    @JsonProperty("book_id")
    private String bookId;
}
