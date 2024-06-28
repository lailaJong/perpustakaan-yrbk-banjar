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
import tugasakhir.library.model.dto.BookStockDetail;
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
    @Qualifier(ApplicationConstant.BEAN_JDBC_POSTGRES)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(ApplicationConstant.PROPERTIES_NAME)
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

    private static final class BookStockDetailRowMapper implements RowMapper<BookStockDetail> {
        @Override
        public BookStockDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookStockDetail bookStockDetail = new BookStockDetail();
            bookStockDetail.setBookStockId(rs.getString("book_stock_id"));
            bookStockDetail.setBookId(rs.getString("book_id"));
            bookStockDetail.setBookTitle(rs.getString("book_title"));
            bookStockDetail.setStock(rs.getInt("stock"));
            return bookStockDetail;
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

    // Get a book stock by book id
    public BookStock getBookStockByBookId(String bookId) {
        try{
            log.info("[GET BOOK STOCK BY BOOK ID][{}][{}}]", bookId, applicationProperties.getGET_BOOK_STOCK_BY_BOOK_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookId", bookId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOK_STOCK_BY_BOOK_ID(), paramSource, new BookStockRowMapper());
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

    public List<BookStockDetail> getAllBookStockDetails() {
        try{
            log.info("[GET ALL BOOK STOCK DETAILS][{}]", applicationProperties.getGET_ALL_BOOK_STOCK_DETAILS());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOK_STOCK_DETAILS(), new BookStockDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public List<BookStockDetail> getAllBookStockDetailsByBookTitle(String bookTitle) {
        try{
            bookTitle = "%".concat(bookTitle).concat("%");
            SqlParameterSource paramSource = new MapSqlParameterSource("bookTitle", bookTitle);
            log.info("[GET ALL BOOK STOCK DETAILS BY BOOK TITLE][{}]", applicationProperties.getGET_ALL_BOOK_STOCK_DETAILS_BY_BOOK_TITLE());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOK_STOCK_DETAILS_BY_BOOK_TITLE(), paramSource, new BookStockDetailRowMapper());
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
