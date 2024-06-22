package tugasakhir.library.model.request.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateAuthorRq {
    @JsonProperty("author_id")
    @NotBlank(message = "Author ID is mandatory")
    private String authorId;

    @JsonProperty("author_name")
    @NotBlank(message = "Author name is mandatory")
    private String authorName;
}
