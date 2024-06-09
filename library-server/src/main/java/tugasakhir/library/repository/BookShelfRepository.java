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
import tugasakhir.library.model.entity.BookShelf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class BookShelfRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;
    private static final class BookShelfRowMapper implements RowMapper<BookShelf> {
        @Override
        public BookShelf mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookShelf bookShelf = new BookShelf();
            bookShelf.setBookShelfId(rs.getInt("book_shelf_id"));
            bookShelf.setBookShelfCode(rs.getString("book_shelf_code"));
            return bookShelf;
        }
    }

    // Add a bookshelf
    public void addBookShelf(BookShelf bookShelf) {
        String sql = "INSERT INTO book_shelf (book_shelf_id, book_shelf_code) VALUES (:bookShelfId, :bookShelfCode)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookShelf);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get a bookshelf by ID
    public BookShelf getBookShelfById(int bookShelfId) {
        String sql = "SELECT * FROM book_shelf WHERE book_shelf_id = :bookShelfId";
        SqlParameterSource paramSource = new MapSqlParameterSource("bookShelfId", bookShelfId);
        return jdbcTemplate.queryForObject(sql, paramSource, new BookShelfRowMapper());
    }

    // Update a bookshelf
    public void updateBookShelf(BookShelf bookShelf) {
        String sql = "UPDATE book_shelf SET book_shelf_code = :bookShelfCode WHERE book_shelf_id = :bookShelfId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookShelf);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete a bookshelf
    public void deleteBookShelf(int bookShelfId) {
        String sql = "DELETE FROM book_shelf WHERE book_shelf_id = :bookShelfId";
        SqlParameterSource paramSource = new MapSqlParameterSource("bookShelfId", bookShelfId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all bookshelves
    public List<BookShelf> getAllBookShelves() {
        String sql = "SELECT * FROM book_shelf";
        return jdbcTemplate.query(sql, new BookShelfRowMapper());
    }
}
