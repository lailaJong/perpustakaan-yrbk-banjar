package tugasakhir.library.model.request.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdatePublisherRq {
    @JsonProperty("publisher_id")
    private String publisherId;

    @JsonProperty("publisher_name")
    private String publisherName;
}
