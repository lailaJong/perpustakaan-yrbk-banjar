package tugasakhir.library.model.request.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class AuthorRq {
    @Nullable
    @JsonProperty("author_id")
    private String authorId;

    @JsonProperty("author_name")
    private String authorName;
}

