package tugasakhir.library.model.request.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class AuthorRq {

    @JsonProperty("author_name")
    @NotBlank(message = "Author name is mandatory")
    @Pattern(
            regexp = "^(?!.*\\d)(?!.*[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>/?]).{1,60}$|^[A-Z][a-z]*(?: [A-Z][a-z]*){0,59}$",
            message = "Author name must start with a capital letter, each word starts with a capital letter, must not use numbers or punctuation, and must be at most 60 characters long including spaces"
    )
    @Schema(example = "String")
    private String authorName;
}

