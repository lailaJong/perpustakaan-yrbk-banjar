package tugasakhir.library.model.request.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateCategoryRq {
    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("category_name")
    private String categoryName;
}
