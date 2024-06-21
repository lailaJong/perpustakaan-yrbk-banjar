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
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class BookShelfRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
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
            log.info("[ADD AUTHOR][{}]", applicationProperties.getINSERT_BOOKSHELF());
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
            log.info("[UPDATE AUTHOR BY ID][{}][{}]", bookShelf.getBookShelfId(), applicationProperties.getUPDATE_BOOKSHELF_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookShelf);
            jdbcTemplate.update(applicationProperties.getUPDATE_BOOKSHELF_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete a bookshelf
    public void deleteBookShelf(String bookShelfId) {
        try{
            log.info("[DELETE AUTHOR BY ID][{}][{}]", bookShelfId, applicationProperties.getDELETE_BOOKSHELF_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookShelfId", bookShelfId);
            jdbcTemplate.update(applicationProperties.getDELETE_BOOKSHELF_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all bookshelves
    public List<BookShelf> getAllBookShelves() {
        try{
            log.info("[GET ALL BOOKSHELF][{}]", applicationProperties.getGET_ALL_BOOKSHELF());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOKSHELF(), new BookShelfRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateBookShelfId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_BOOKSHELF(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("BKS%03d", suffix);
    }
}
