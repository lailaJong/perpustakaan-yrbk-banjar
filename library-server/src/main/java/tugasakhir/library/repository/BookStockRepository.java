package tugasakhir.library.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tugasakhir.library.config.properties.ApplicationProperties;
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
@Slf4j
public class BookStockRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class BookStockRowMapper implements RowMapper<BookStock> {
        @Override
        public BookStock mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookStock bookStock = new BookStock();
            bookStock.setBookStockId(rs.getString("book_stock_id"));
            bookStock.setBookId(rs.getString("book_id"));
            bookStock.setStock(rs.getInt("stock"));
            return bookStock;
        }
    }

    // Add a book stock
    public void addBookStock(BookStock bookStock) {
        try{
            log.info("[ADD BOOK STOCK][{}]", applicationProperties.getINSERT_BOOK_STOCK());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookStock);
            jdbcTemplate.update(applicationProperties.getINSERT_BOOK_STOCK(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get a book stock by ID
    public BookStock getBookStockById(String bookStockId) {
        try{
            log.info("[GET BOOK STOCK BY ID][{}][{}}]", bookStockId, applicationProperties.getGET_BOOK_STOCK_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookStockId", bookStockId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOK_STOCK_BY_ID(), paramSource, new BookStockRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update a book stock
    public void updateBookStock(BookStock bookStock) {
        try{
            log.info("[UPDATE BOOK STOCK BY ID][{}][{}]", bookStock.getBookId(), applicationProperties.getUPDATE_BOOK_STOCK_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookStock);
            jdbcTemplate.update(applicationProperties.getUPDATE_BOOK_STOCK_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete a book stock
    public void deleteBookStock(String bookStockId) {
        try{
            log.info("[DELETE BOOK STOCK BY ID][{}][{}]", bookStockId, applicationProperties.getDELETE_BOOK_STOCK_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookStockId", bookStockId);
            jdbcTemplate.update(applicationProperties.getDELETE_BOOK_STOCK_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all book stocks
    public List<BookStock> getAllBookStocks() {
        try{
            log.info("[GET ALL BOOK STOCK][{}]", applicationProperties.getGET_ALL_BOOK_STOCK());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOK_STOCK(), new BookStockRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateBookStockId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_BOOK_STOCK(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("STK%03d", suffix);
    }
}
