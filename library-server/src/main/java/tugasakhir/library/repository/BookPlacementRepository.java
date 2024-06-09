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
import tugasakhir.library.model.entity.BookPlacement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class BookPlacementRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;
    private static final class BookPlacementRowMapper implements RowMapper<BookPlacement> {
        @Override
        public BookPlacement mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookPlacement bookPlacement = new BookPlacement();
            bookPlacement.setBookPlacementId(rs.getInt("book_placement_id"));
            bookPlacement.setBookShelfId(rs.getInt("book_shelf_id"));
            bookPlacement.setBookId(rs.getInt("book_id"));
            return bookPlacement;
        }
    }

    // Add a book placement
    public void addBookPlacement(BookPlacement bookPlacement) {
        String sql = "INSERT INTO book_placement (book_placement_id, book_shelf_id, book_id) " +
                "VALUES (:bookPlacementId, :bookShelfId, :bookId)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookPlacement);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get a book placement by ID
    public BookPlacement getBookPlacementById(int bookPlacementId) {
        String sql = "SELECT * FROM book_placement WHERE book_placement_id = :bookPlacementId";
        SqlParameterSource paramSource = new MapSqlParameterSource("bookPlacementId", bookPlacementId);
        return jdbcTemplate.queryForObject(sql, paramSource, new BookPlacementRowMapper());
    }

    // Update a book placement
    public void updateBookPlacement(BookPlacement bookPlacement) {
        String sql = "UPDATE book_placement SET book_shelf_id = :bookShelfId, book_id = :bookId " +
                "WHERE book_placement_id = :bookPlacementId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookPlacement);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete a book placement
    public void deleteBookPlacement(int bookPlacementId) {
        String sql = "DELETE FROM book_placement WHERE book_placement_id = :bookPlacementId";
        SqlParameterSource paramSource = new MapSqlParameterSource("bookPlacementId", bookPlacementId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all book placements
    public List<BookPlacement> getAllBookPlacements() {
        String sql = "SELECT * FROM book_placement";
        return jdbcTemplate.query(sql, new BookPlacementRowMapper());
    }
}
