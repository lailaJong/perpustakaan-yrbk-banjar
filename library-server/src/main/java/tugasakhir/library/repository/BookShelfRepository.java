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
import tugasakhir.library.model.entity.BookShelf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class BookShelfRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_JDBC_POSTGRES)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(ApplicationConstant.PROPERTIES_NAME)
    protected ApplicationProperties applicationProperties;

    private static final class BookShelfRowMapper implements RowMapper<BookShelf> {
        @Override
        public BookShelf mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookShelf bookShelf = new BookShelf();
            bookShelf.setBookShelfId(rs.getString("book_shelf_id"));
            bookShelf.setBookShelfCode(rs.getString("book_shelf_code"));
            return bookShelf;
        }
    }

    // Add a bookshelf
    public void addBookShelf(BookShelf bookShelf) {
        try{
            log.info("[ADD BOOK SHELF][{}]", applicationProperties.getINSERT_BOOKSHELF());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookShelf);
            jdbcTemplate.update(applicationProperties.getINSERT_BOOKSHELF(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get a bookshelf by ID
    public BookShelf getBookShelfById(String bookShelfId) {
        try{
            log.info("[GET AUTHOR BY ID][{}][{}}]", bookShelfId, applicationProperties.getGET_BOOKSHELF_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookShelfId", bookShelfId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOKSHELF_BY_ID(), paramSource, new BookShelfRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update a bookshelf
    public void updateBookShelf(BookShelf bookShelf) {
        try{
            log.info("[UPDATE BOOK SHELF BY ID][{}][{}]", bookShelf.getBookShelfId(), applicationProperties.getUPDATE_BOOKSHELF_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookShelf);
            jdbcTemplate.update(applicationProperties.getUPDATE_BOOKSHELF_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete a bookshelf
    public void deleteBookShelf(String bookShelfId) {
        try{
            log.info("[DELETE BOOK SHELF BY ID][{}][{}]", bookShelfId, applicationProperties.getDELETE_BOOKSHELF_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookShelfId", bookShelfId);
            jdbcTemplate.update(applicationProperties.getDELETE_BOOKSHELF_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all bookshelves
    public List<BookShelf> getAllBookShelves() {
        try{
            log.info("[GET ALL BOOK SHELVES][{}]", applicationProperties.getGET_ALL_BOOKSHELF());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOKSHELF(), new BookShelfRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<BookShelf> getAllBookShelfByCode(String bookShelfCode) {
        try {
            bookShelfCode = "%".concat(bookShelfCode).concat("%");
            log.info("[GET BOOK SHELF BY CODE][{}][{}]", bookShelfCode, applicationProperties.getGET_ALL_BOOK_SHELF_BY_CODE());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookShelfCode", bookShelfCode);
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOK_SHELF_BY_CODE(), paramSource, new BookShelfRowMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public BookShelf getBookShelfByCode(String bookShelfCode) {
        try {
            log.info("[GET BOOK SHELF BY CODE][{}][{}]", bookShelfCode, applicationProperties.getGET_BOOK_SHELF_BY_CODE());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookShelfCode", bookShelfCode);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOK_SHELF_BY_CODE(), paramSource, new BookShelfRowMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateBookShelfId() {
        try {
            int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_BOOKSHELF(), (SqlParameterSource) null, Integer.class);
            int suffix = count + 1;
            return String.format("BKS%03d", suffix);
        }catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean existsByBookShelfCode(String bookShelfCode) {
        try{
            log.info("[CHECK BOOK SHELF CODE IS EXIST OR NOT][{}][{}]", applicationProperties.getGET_EXIST_BOOK_SHELF_CODE(), bookShelfCode);
            Map<String, Object> params = new HashMap<>();
            params.put("bookShelfCode", bookShelfCode);
            int count = jdbcTemplate.queryForObject(applicationProperties.getGET_EXIST_BOOK_SHELF_CODE(), params, Integer.class);
            log.info("[COUNT: {}]", count);
            return count > 0;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean existsByBookShelfId(String bookShelfId) {
        try{
            log.info("[CHECK BOOK SHELF ID IS EXIST OR NOT][{}][{}]", applicationProperties.getGET_EXIST_BOOK_SHELF_CODE(), bookShelfId);
            Map<String, Object> params = new HashMap<>();
            params.put("bookShelfId", bookShelfId);
            int count = jdbcTemplate.queryForObject(applicationProperties.getGET_EXIST_BOOK_SHELF_CODE(), params, Integer.class);
            log.info("[COUNT: {}]", count);
            return count > 0;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
