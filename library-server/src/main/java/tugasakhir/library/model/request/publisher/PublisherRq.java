package tugasakhir.library.model.request.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;
import tugasakhir.library.utils.validation.ValidName;

@Data
@Accessors(chain = true)
@Schema
public class PublisherRq {

    @JsonProperty("publisher_name")
    @NotBlank(message = "Publisher name is mandatory")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*(?: [A-Z][a-zA-Z]*)*(?:[.()])?$", message = "Invalid publisher name format")
    private String publisherName;
}

