package tugasakhir.library.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Putri Mele
 * on 09/06/2024
 */
@Data
@NoArgsConstructor
@NotNull
@Accessors(chain = true)
public class BookDetail {
        private String bookId;
        private String bookTitle;
        private String authorName;
        private String publisherName;
        private String categoryName;
        private String publicationYear;
        private int stock;
        private String language;
        private String isbn;
        private int numberOfPages;
        private String bookShelfId;
        private String synopsis;
}
