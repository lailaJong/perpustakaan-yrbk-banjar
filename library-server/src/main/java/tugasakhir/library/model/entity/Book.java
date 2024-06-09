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
@Accessors(chain = true)
public class Book {
        private int bookId;
        private String bookTitle;
        private int categoryId;
        private int publisherId;
        private int authorId;
        private String language;
        private String isbn;
        private int numberOfPages;
        private String publicationYear;
        private String synopsis;
}
