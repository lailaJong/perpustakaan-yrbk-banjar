package tugasakhir.library.model.request.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
    private String categoryName;
}

