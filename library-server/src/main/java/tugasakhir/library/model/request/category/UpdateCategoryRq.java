package tugasakhir.library.model.request.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateCategoryRq {
    @JsonProperty("category_id")
    @NotBlank(message = "Category ID is mandatory")
    private String categoryId;

    @JsonProperty("category_name")
    @NotBlank(message = "Category name is mandatory")
    private String categoryName;
}
