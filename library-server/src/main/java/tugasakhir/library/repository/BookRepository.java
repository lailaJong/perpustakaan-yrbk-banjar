package tugasakhir.library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tugasakhir.library.config.variable.ApplicationConstant;
import tugasakhir.library.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class BookRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    private static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setBookTitle(rs.getString("book_title"));
            book.setCategoryId(rs.getInt("category_id"));
            book.setPublisherId(rs.getInt("publisher_id"));
            book.setAuthorId(rs.getInt("author_id"));
            book.setLanguage(rs.getString("language"));
            book.setIsbn(rs.getString("isbn"));
            book.setNumberOfPages(rs.getInt("number_of_pages"));
            book.setPublicationYear(rs.getString("publication_year"));
            book.setSynopsis(rs.getString("synopsis"));
            return book;
        }
    }

    // Add a book
    public void addBook(Book book) {
        String sql = "INSERT INTO book (book_id, book_title, category_id, publisher_id, author_id, language, isbn, number_of_pages, publication_year, synopsis) " +
                "VALUES (:bookId, :bookTitle, :categoryId, :publisherId, :authorId, :language, :isbn, :numberOfPages, :publicationYear, :synopsis)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get a book by ID
    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM book WHERE book_id = :bookId";
        SqlParameterSource paramSource = new MapSqlParameterSource("bookId", bookId);
        return jdbcTemplate.queryForObject(sql, paramSource, new BookRowMapper());
    }

    // Update a book
    public void updateBook(Book book) {
        String sql = "UPDATE book SET book_title = :bookTitle, category_id = :categoryId, publisher_id = :publisherId, author_id = :authorId, language = :language, " +
                "isbn = :isbn, number_of_pages = :numberOfPages, publication_year = :publicationYear, synopsis = :synopsis WHERE book_id = :bookId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete a book
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM book WHERE book_id = :bookId";
        SqlParameterSource paramSource = new MapSqlParameterSource("bookId", bookId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all books
    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }
}

