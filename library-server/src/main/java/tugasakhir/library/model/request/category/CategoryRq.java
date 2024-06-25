package tugasakhir.library.model.request.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class CategoryRq {
    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("category_name")
    @NotBlank(message = "Category name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Category name can contain alphabetic characters only")
    private String categoryName;
}

