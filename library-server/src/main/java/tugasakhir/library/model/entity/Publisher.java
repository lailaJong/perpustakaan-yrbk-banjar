package tugasakhir.library.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Data
@NoArgsConstructor
@Accessors (chain = true)
public class Publisher {
    private String publisherId;
    private String publisherName;
}
