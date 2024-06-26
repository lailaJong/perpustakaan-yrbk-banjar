package tugasakhir.library.model.request.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema
//public class BookRq {
//
//    @JsonProperty("book_id")
//    private String bookId;
//
//    @JsonProperty("book_title")
//    @NotBlank(message = "Book title is mandatory")
//    private String bookTitle;
//
//    @JsonProperty("category_id")
//    @NotBlank(message = "Category ID is mandatory")
//    private String categoryId;
//
//    @JsonProperty("publisher_id")
//    @NotBlank(message = "Publisher ID is mandatory")
//    private String publisherId;
//
//    @JsonProperty("author_id")
//    @NotBlank(message = "Author ID is mandatory")
//    private String authorId;
//
//    @JsonProperty("bookshelf_Id")
//    @NotBlank(message = "Bookshelf ID is mandatory")
//    private String bookShelfId;
//
//    @JsonProperty("language")
//    @NotBlank(message = "Language is mandatory")
//    private String language;
//
//    @JsonProperty("isbn")
//    @NotBlank(message = "ISBN is mandatory")
//    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "ISBN format is invalid")
//    private String isbn;
//
//    @JsonProperty("number_of_pages")
//    @Min(value = 1, message = "Number of pages must be at least 1")
//    private int numberOfPages;
//
//    @JsonProperty("publication_year")
//    @NotBlank(message = "Publication year is mandatory")
//    @Pattern(regexp = "^\\d{4}$", message = "Publication year must be a valid year")
//    private String publicationYear;
//
//    @JsonProperty("synopsis")
//    @NotBlank(message = "Synopsis is mandatory")
//    private String synopsis;
//}
public class BookRq {

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("book_title")
    @NotBlank(message = "Book title is mandatory")
    private String bookTitle;

    @JsonProperty("category_name")
    @NotBlank(message = "Category Name is mandatory")
    private String categoryName;

    @JsonProperty("publisher_name")
    @NotBlank(message = "Publisher Name is mandatory")
    private String publisherName;

    @JsonProperty("author_name")
    @NotBlank(message = "Author Name is mandatory")
    private String authorName;

    @JsonProperty("bookshelf_code")
    @NotBlank(message = "Bookshelf Code is mandatory")
    private String bookShelfCode;

    @JsonProperty("language")
    @NotBlank(message = "Language is mandatory")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Language must contain only alphabetic characters and spaces")
    private String language;

    @JsonProperty("isbn")
    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "ISBN format is invalid")
    private String isbn;

    @JsonProperty("number_of_pages")
    @Min(value = 1, message = "Number of pages must be at least 1")
    @Max(value = 1500, message = "Number of pages must be at most 1500")
    private int numberOfPages;

    @JsonProperty("publication_year")
    @NotBlank(message = "Publication year is mandatory")
    @Pattern(regexp = "^\\d{4}$", message = "Publication year must be a valid year")
    private String publicationYear;

    @JsonProperty("synopsis")
    @NotBlank(message = "Synopsis is mandatory")
    private String synopsis;

    @JsonProperty("stock")
    @Min(value = 1, message = "Stock must be at least 1")
    @Max(value = 1000, message = "Stock must be at most 1000")
    private int stock;
}

