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
import tugasakhir.library.model.entity.BorrowingDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class BorrowingDetailRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class BorrowingDetailRowMapper implements RowMapper<BorrowingDetail> {
        @Override
        public BorrowingDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            BorrowingDetail borrowingDetail = new BorrowingDetail();
            borrowingDetail.setBorrowingId(rs.getString("borrowing_id"));
            borrowingDetail.setUserId(rs.getString("user_id"));
            borrowingDetail.setBookId(rs.getString("book_id"));
            borrowingDetail.setBorrowingDate(rs.getDate("borrowing_date"));
            borrowingDetail.setReturnDate(rs.getDate("return_date"));
            borrowingDetail.setActualReturnDate(rs.getDate("actual_return_date"));
            borrowingDetail.setStatus(rs.getString("status"));
            return borrowingDetail;
        }
    }

    // Add a borrowing detail
    public void addBorrowingDetail(BorrowingDetail borrowingDetail) {
        try{
            log.info("[ADD BORROWING DETAIL][{}]", applicationProperties.getINSERT_BORROWING_DETAIL());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(borrowingDetail);
            jdbcTemplate.update(applicationProperties.getINSERT_BORROWING_DETAIL(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get a borrowing detail by ID
    public BorrowingDetail getBorrowingDetailById(String borrowingId) {
        try{
            log.info("[GET BORROWING DETAIL BY ID][{}][{}}]", borrowingId, applicationProperties.getGET_BORROWING_DETAIL_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("borrowingId", borrowingId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BORROWING_DETAIL_BY_ID(), paramSource, new BorrowingDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update a borrowing detail
    public void updateBorrowingDetail(BorrowingDetail borrowingDetail) {
        try{
            log.info("[UPDATE BORROWING DETAIL BY ID][{}][{}]", borrowingDetail.getBorrowingId(), applicationProperties.getUPDATE_AUTHOR_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(borrowingDetail);
            jdbcTemplate.update(applicationProperties.getUPDATE_BORROWING_DETAIL_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete a borrowing detail
    public void deleteBorrowingDetail(String borrowingId) {
        try{
            log.info("[DELETE BORROWING DETAIL BY ID][{}][{}]", borrowingId, applicationProperties.getDELETE_BORROWING_DETAIL_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("borrowingId", borrowingId);
            jdbcTemplate.update(applicationProperties.getDELETE_BORROWING_DETAIL_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all borrowing details
    public List<BorrowingDetail> getAllBorrowingDetails() {
        try{
            log.info("[GET ALL BORROWING DETAIL][{}]", applicationProperties.getGET_ALL_BORROWING_DETAIL());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BORROWING_DETAIL(), new BorrowingDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateBorrowingDetailId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_BORROWING_DETAIL(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("BRW%03d", suffix);
    }
}
