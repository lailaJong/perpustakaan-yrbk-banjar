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
import tugasakhir.library.model.entity.BookStock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class BookStockRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    private static final class BookStockRowMapper implements RowMapper<BookStock> {
        @Override
        public BookStock mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookStock bookStock = new BookStock();
            bookStock.setBookStockId(rs.getInt("book_stock_id"));
            bookStock.setBookId(rs.getInt("book_id"));
            bookStock.setStock(rs.getInt("stock"));
            return bookStock;
        }
    }

    // Add a book stock
    public void addBookStock(BookStock bookStock) {
        String sql = "INSERT INTO book_stock (book_stock_id, book_id, stock) VALUES (:bookStockId, :bookId, :stock)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookStock);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get a book stock by ID
    public BookStock getBookStockById(int bookStockId) {
        String sql = "SELECT * FROM book_stock WHERE book_stock_id = :bookStockId";
        SqlParameterSource paramSource = new MapSqlParameterSource("bookStockId", bookStockId);
        return jdbcTemplate.queryForObject(sql, paramSource, new BookStockRowMapper());
    }

    // Update a book stock
    public void updateBookStock(BookStock bookStock) {
        String sql = "UPDATE book_stock SET book_id = :bookId, stock = :stock WHERE book_stock_id = :bookStockId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookStock);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete a book stock
    public void deleteBookStock(int bookStockId) {
        String sql = "DELETE FROM book_stock WHERE book_stock_id = :bookStockId";
        SqlParameterSource paramSource = new MapSqlParameterSource("bookStockId", bookStockId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all book stocks
    public List<BookStock> getAllBookStocks() {
        String sql = "SELECT * FROM book_stock";
        return jdbcTemplate.query(sql, new BookStockRowMapper());
    }
}
