package tugasakhir.library.model.request.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdatePublisherRq {
    @JsonProperty("publisher_id")
    @NotBlank(message = "Publisher ID is mandatory")
    private String publisherId;

    @JsonProperty("publisher_name")
    @NotBlank(message = "Publisher name is mandatory")
    private String publisherName;
}
