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
import tugasakhir.library.model.dto.BorrowingDetail;
import tugasakhir.library.model.dto.BorrowingHistories;
import tugasakhir.library.model.dto.BorrowingHistoriesUser;
import tugasakhir.library.model.entity.Borrowing;

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

    private static final class BorrowingRowMapper implements RowMapper<Borrowing> {
        @Override
        public Borrowing mapRow(ResultSet rs, int rowNum) throws SQLException {
            Borrowing borrowingDetail = new Borrowing();
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

    private static final class BorrowingDetailRowMapper implements RowMapper<BorrowingDetail> {
        @Override
        public BorrowingDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            BorrowingDetail borrowingDetail = new BorrowingDetail();
            borrowingDetail.setBorrowingId(rs.getString("borrowing_id"));
            borrowingDetail.setBookTitle(rs.getString("book_title"));
            borrowingDetail.setStatus(rs.getString("status"));
            borrowingDetail.setBorrowingDate(rs.getDate("borrowing_date"));
            borrowingDetail.setReturnDate(rs.getDate("return_date"));
            return borrowingDetail;
        }
    }

    private static final class BorrowingHistoriesRowMapper implements RowMapper<BorrowingHistories> {
        @Override
        public BorrowingHistories mapRow(ResultSet rs, int rowNum) throws SQLException {
            BorrowingHistories borrowingHistories = new BorrowingHistories();
            borrowingHistories.setBorrowingId(rs.getString("borrowing_id"));
            borrowingHistories.setMemberName(rs.getString("name"));
            borrowingHistories.setBookId(rs.getString("book_id"));
            borrowingHistories.setStatus(rs.getString("status"));
            borrowingHistories.setBorrowingDate(rs.getDate("borrowing_date"));
            borrowingHistories.setReturnDate(rs.getDate("return_date"));
            borrowingHistories.setActualReturnDate(rs.getDate("actual_return_date"));
            return borrowingHistories;
        }
    }

    private static final class BorrowingHistoriesUserRowMapper implements RowMapper<BorrowingHistoriesUser> {
        @Override
        public BorrowingHistoriesUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            BorrowingHistoriesUser borrowingHistoriesUser = new BorrowingHistoriesUser();
            borrowingHistoriesUser.setBorrowingId(rs.getString("borrowing_id"));
            borrowingHistoriesUser.setBookTitle(rs.getString("book_title"));
            borrowingHistoriesUser.setBorrowingDate(rs.getDate("borrowing_date"));
            borrowingHistoriesUser.setReturnDate(rs.getDate("return_date"));
            borrowingHistoriesUser.setActualReturnDate(rs.getDate("actual_return_date"));
            return borrowingHistoriesUser;
        }
    }

    // Add a borrowing detail
    public void addBorrowingDetail(Borrowing borrowingDetail) {
        try{
            log.info("[ADD BORROWING DETAIL][{}]", applicationProperties.getINSERT_BORROWING_DETAIL());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(borrowingDetail);
            jdbcTemplate.update(applicationProperties.getINSERT_BORROWING_DETAIL(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get a borrowing detail by ID
    public Borrowing getBorrowingDetailById(String borrowingId) {
        try{
            log.info("[GET BORROWING DETAIL BY ID][{}][{}}]", borrowingId, applicationProperties.getGET_BORROWING_DETAIL_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("borrowingId", borrowingId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BORROWING_DETAIL_BY_ID(), paramSource, new BorrowingRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public int getCountBorrowingStatusByUserId(String userId, String borrowingStatus) {
        try{

            log.info("[GET COUNT BORROWING DETAIL BY USER ID AND STATUS][{}][{}][{}]", userId, borrowingStatus, applicationProperties.getGET_COUNT_BORROWING_LATE_STATUS_BY_USER_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("userId", userId)
                    .addValue("borrowingStatus", borrowingStatus);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_BORROWING_LATE_STATUS_BY_USER_ID(), paramSource, Integer.class);
        }catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }
    }

    public int getCountAllBorrowingHistoryByUserId(String userId) {
        try{
            log.info("[GET COUNT ALL BORROWING HISTORY BY USER ID][{}][{}}]", userId, applicationProperties.getGET_COUNT_RETURN_STATUS_BY_USER_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("userId", userId)
                    .addValue("returnStatus", applicationProperties.getReturnedStatus())
                    .addValue("lostStatus", applicationProperties.getLostStatus());
            return jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_RETURN_STATUS_BY_USER_ID(), paramSource, Integer.class);
        }catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }
    }

    // Update a borrowing detail
    public void updateBorrowingDetail(Borrowing borrowingDetail) {
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
    public List<Borrowing> getAllBorrowingDetails() {
        try{
            log.info("[GET ALL BORROWING DETAIL][{}]", applicationProperties.getGET_ALL_BORROWING_DETAIL());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BORROWING_DETAIL(), new BorrowingRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Get all borrowing details by user id
    public List<BorrowingDetail> getAllBorrowingDetailsByUserId(String userId) {
        try{
            log.info("[GET ALL BORROWING DETAILS BY USER ID][{}][{}]", userId, applicationProperties.getGET_ALL_BORROWING_DETAILS_BY_USER_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("userId", userId)
                    .addValue("status", applicationProperties.getBorrowedStatus());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BORROWING_DETAILS_BY_USER_ID(), paramSource, new BorrowingDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Get all borrowing details by user id and book title
    public List<BorrowingDetail> getAllBorrowingDetailsByUserIdAndBookTitle(String userId, String bookTitle) {
        try{
            bookTitle = "%".concat(bookTitle).concat("%");
            log.info("[GET ALL BORROWING DETAILS BY USER ID AND BOOK TITLE][{}][{}][{}]", userId, bookTitle, applicationProperties.getGET_ALL_BORROWING_DETAILS_BY_USER_ID_AND_BOOK_TITLE());
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("userId", userId)
                    .addValue("status", applicationProperties.getBorrowedStatus())
                    .addValue("bookTitle", bookTitle);
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BORROWING_DETAILS_BY_USER_ID_AND_BOOK_TITLE(), paramSource, new BorrowingDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Get all borrowing details when status "selesai"
    public List<BorrowingHistories> getAllBorrowingHistories(String status) {
        try{
            log.info("[GET ALL BORROWING HISTORIES][{}][{}]", applicationProperties.getGET_ALL_BORROWING_DETAILS_BY_STATUS(), status);
            SqlParameterSource paramSource = new MapSqlParameterSource("status", status);
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BORROWING_DETAILS_BY_STATUS(), paramSource, new BorrowingHistoriesRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Get all borrowing details when status "selesai" based on member name
    public List<BorrowingHistories> getAllBorrowingHistoriesByMemberName(String status, String name) {
        try{
            name = "%".concat("%");
            log.info("[GET ALL BORROWING HISTORIES BY NAME][{}][{}][{}]", applicationProperties.getGET_ALL_BORROWING_DETAILS_BY_STATUS_AND_MEMBER_NAME(), status, name);
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("status", status)
                    .addValue("name", name);
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BORROWING_DETAILS_BY_STATUS_AND_MEMBER_NAME(), paramSource, new BorrowingHistoriesRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Get all borrowing details for user when status "selesai" atau "hilang"
    public List<BorrowingHistoriesUser> getAllBorrowingHistoriesByUserId(String userId) {
        try{
            log.info("[GET ALL BORROWING HISTORIES BY USER ID][{}][{}]", userId, applicationProperties.getGET_ALL_BORROWING_DETAILS_USER_BY_USER_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("userId", userId)
                    .addValue("returnedStatus", applicationProperties.getReturnedStatus())
                    .addValue("lostStatus", applicationProperties.getLostStatus());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BORROWING_DETAILS_USER_BY_USER_ID(), paramSource, new BorrowingHistoriesUserRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Get all borrowing details for user when status "selesai" atau "hilang" by book title
    public List<BorrowingHistoriesUser> getAllBorrowingHistoriesByUserIdAndBookTitle(String userId, String bookTitle) {
        try{
            bookTitle = "%".concat(bookTitle).concat("%");
            log.info("[GET ALL BORROWING HISTORIES BY USER ID][{}][{}][{}]", userId, bookTitle, applicationProperties.getGET_ALL_BORROWING_DETAILS_USER_BY_USER_ID_AND_BOOK_TILTE());
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("userId", userId)
                    .addValue("returnedStatus", applicationProperties.getReturnedStatus())
                    .addValue("lostStatus", applicationProperties.getLostStatus())
                    .addValue("bookTitle", bookTitle);
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BORROWING_DETAILS_USER_BY_USER_ID_AND_BOOK_TILTE(), paramSource, new BorrowingHistoriesUserRowMapper());
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
