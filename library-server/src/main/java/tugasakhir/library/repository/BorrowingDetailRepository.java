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
import tugasakhir.library.model.entity.BorrowingDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class BorrowingDetailRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;
    private static final class BorrowingDetailRowMapper implements RowMapper<BorrowingDetail> {
        @Override
        public BorrowingDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            BorrowingDetail borrowingDetail = new BorrowingDetail();
            borrowingDetail.setBorrowingId(rs.getInt("borrowing_id"));
            borrowingDetail.setUserId(rs.getInt("user_id"));
            borrowingDetail.setBookId(rs.getInt("book_id"));
            borrowingDetail.setBorrowingDate(rs.getDate("borrowing_date"));
            borrowingDetail.setReturnDate(rs.getDate("return_date"));
            borrowingDetail.setActualReturnDate(rs.getDate("actual_return_date"));
            borrowingDetail.setStatus(rs.getString("status"));
            return borrowingDetail;
        }
    }

    // Add a borrowing detail
    public void addBorrowingDetail(BorrowingDetail borrowingDetail) {
        String sql = "INSERT INTO borrowing_detail (borrowing_id, user_id, book_id, borrowing_date, return_date, actual_return_date, status) " +
                "VALUES (:borrowingId, :userId, :bookId, :borrowingDate, :returnDate, :actualReturnDate, :status)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(borrowingDetail);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get a borrowing detail by ID
    public BorrowingDetail getBorrowingDetailById(int borrowingId) {
        String sql = "SELECT * FROM borrowing_detail WHERE borrowing_id = :borrowingId";
        SqlParameterSource paramSource = new MapSqlParameterSource("borrowingId", borrowingId);
        return jdbcTemplate.queryForObject(sql, paramSource, new BorrowingDetailRowMapper());
    }

    // Update a borrowing detail
    public void updateBorrowingDetail(BorrowingDetail borrowingDetail) {
        String sql = "UPDATE borrowing_detail SET user_id = :userId, book_id = :bookId, borrowing_date = :borrowingDate, " +
                "return_date = :returnDate, actual_return_date = :actualReturnDate, status = :status " +
                "WHERE borrowing_id = :borrowingId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(borrowingDetail);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete a borrowing detail
    public void deleteBorrowingDetail(int borrowingId) {
        String sql = "DELETE FROM borrowing_detail WHERE borrowing_id = :borrowingId";
        SqlParameterSource paramSource = new MapSqlParameterSource("borrowingId", borrowingId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all borrowing details
    public List<BorrowingDetail> getAllBorrowingDetails() {
        String sql = "SELECT * FROM borrowing_detail";
        return jdbcTemplate.query(sql, new BorrowingDetailRowMapper());
    }
}
