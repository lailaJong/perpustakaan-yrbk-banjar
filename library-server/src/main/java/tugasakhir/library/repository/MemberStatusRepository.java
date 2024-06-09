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
import tugasakhir.library.model.entity.MemberStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class MemberStatusRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;
    private static final class MemberStatusRowMapper implements RowMapper<MemberStatus> {
        @Override
        public MemberStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
            MemberStatus memberStatus = new MemberStatus();
            memberStatus.setMemberStatusId(rs.getInt("member_status_id"));
            memberStatus.setStatus(rs.getString("status"));
            return memberStatus;
        }
    }

    // Add a member status
    public void addMemberStatus(MemberStatus memberStatus) {
        String sql = "INSERT INTO member_status (member_status_id, status) VALUES (:memberStatusId, :status)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberStatus);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get a member status by ID
    public MemberStatus getMemberStatusById(int memberStatusId) {
        String sql = "SELECT * FROM member_status WHERE member_status_id = :memberStatusId";
        SqlParameterSource paramSource = new MapSqlParameterSource("memberStatusId", memberStatusId);
        return jdbcTemplate.queryForObject(sql, paramSource, new MemberStatusRowMapper());
    }

    // Update a member status
    public void updateMemberStatus(MemberStatus memberStatus) {
        String sql = "UPDATE member_status SET status = :status WHERE member_status_id = :memberStatusId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberStatus);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete a member status
    public void deleteMemberStatus(int memberStatusId) {
        String sql = "DELETE FROM member_status WHERE member_status_id = :memberStatusId";
        SqlParameterSource paramSource = new MapSqlParameterSource("memberStatusId", memberStatusId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all member statuses
    public List<MemberStatus> getAllMemberStatuses() {
        String sql = "SELECT * FROM member_status";
        return jdbcTemplate.query(sql, new MemberStatusRowMapper());
    }
}
