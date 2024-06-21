package tugasakhir.library.model.request.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class PublisherRq {
    @JsonProperty("publisher_id")
    private String publisherId;

    @JsonProperty("publisher_name")
    private String publisherName;
}

