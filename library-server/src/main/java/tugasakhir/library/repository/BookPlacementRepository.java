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
import tugasakhir.library.model.entity.BookPlacement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class BookPlacementRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class BookPlacementRowMapper implements RowMapper<BookPlacement> {
        @Override
        public BookPlacement mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookPlacement bookPlacement = new BookPlacement();
            bookPlacement.setBookPlacementId(rs.getString("book_placement_id"));
            bookPlacement.setBookShelfId(rs.getString("book_shelf_id"));
            bookPlacement.setBookId(rs.getString("book_id"));
            return bookPlacement;
        }
    }

    // Add a book placement
    public void addBookPlacement(BookPlacement bookPlacement) {
        try{
            log.info("[ADD BOOK PLACEMENT][{}]", applicationProperties.getINSERT_BOOK_PLACEMENT());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookPlacement);
            jdbcTemplate.update(applicationProperties.getINSERT_BOOK_PLACEMENT(), paramSource);
        }catch (Exception e){
           log.error(e.getMessage());
        }
    }

    // Get a book placement by ID
    public BookPlacement getBookPlacementById(String bookPlacementId) {
        try{
            log.info("[GET BOOK PLACEMENT BY ID][{}][{}]", bookPlacementId, applicationProperties.getGET_BOOK_PLACEMENT_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookPlacementId", bookPlacementId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOK_PLACEMENT_BY_ID(), paramSource, new BookPlacementRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update a book placement
    public void updateBookPlacement(BookPlacement bookPlacement) {
        try{
            log.info("[UPDATE BOOK PLACEMENT BY ID][{}][{}]", bookPlacement.getBookPlacementId(), applicationProperties.getUPDATE_BOOK_PLACEMENT_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(bookPlacement);
            jdbcTemplate.update(applicationProperties.getUPDATE_BOOK_PLACEMENT_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete a book placement
    public void deleteBookPlacement(String bookPlacementId) {
        try{
            log.info("[DELETE BOOK PLACEMENT BY ID][{}][{}]", bookPlacementId, applicationProperties.getDELETE_BOOK_PLACEMENT_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookPlacementId", bookPlacementId);
            jdbcTemplate.update(applicationProperties.getDELETE_BOOK_PLACEMENT_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all book placements
    public List<BookPlacement> getAllBookPlacements() {
        try{
            log.info("[GET ALL BOOK PLACEMENT][{}]", applicationProperties.getGET_ALL_BOOK_PLACEMENT());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOK_PLACEMENT(), new BookPlacementRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateBookPlacementId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_BOOK_PLACEMENT(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("BPC%03d", suffix);
    }
}
