package tugasakhir.library.model.entity;

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
public class Book {
        private String bookId;
        private String bookTitle;
        private String categoryId;
        private String publisherId;
        private String authorId;
        private String bookShelfId;
        private String language;
        private String isbn;
        private int numberOfPages;
        private String publicationYear;
        private String synopsis;
        private int stock;
}
