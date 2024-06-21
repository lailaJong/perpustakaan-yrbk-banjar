package tugasakhir.library.model.request.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
public class BookRq {
    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("book_title")
    private String bookTitle;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("publisher_id")
    private String publisherId;

    @JsonProperty("author_id")
    private String authorId;

    @JsonProperty("language")
    private String language;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("number_of_pages")
    private int numberOfPages;

    @JsonProperty("publication_year")
    private String publicationYear;

    @JsonProperty("synopsis")
    private String synopsis;

    @JsonProperty("status")
    private String status;
}

