package tugasakhir.library.model.request.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateAuthorRq {
    @JsonProperty("author_id")
    private String authorId;

    @JsonProperty("author_name")
    private String authorName;
}
