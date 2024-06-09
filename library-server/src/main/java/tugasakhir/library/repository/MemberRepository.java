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
import tugasakhir.library.model.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class MemberRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;
    private static final class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberId(rs.getInt("member_id"));
            member.setUserId(rs.getInt("user_id"));
            member.setMemberStatusId(rs.getInt("member_status_id"));
            member.setScoreDetailId(rs.getInt("score_detail_id"));
            member.setName(rs.getString("name"));
            member.setGender(rs.getString("gender"));
            member.setPhoneNumber(rs.getString("phone_number"));
            member.setPlaceOfBirth(rs.getString("place_of_birth"));
            member.setDateOfBirth(rs.getDate("date_of_birth"));
            member.setAddress(rs.getString("address"));
            return member;
        }
    }

    // Add a member
    public void addMember(Member member) {
        String sql = "INSERT INTO member (member_id, user_id, member_status_id, score_detail_id, name, gender, phone_number, place_of_birth, date_of_birth, address) " +
                "VALUES (:memberId, :userId, :memberStatusId, :scoreDetailId, :name, :gender, :phoneNumber, :placeOfBirth, :dateOfBirth, :address)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(member);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get a member by ID
    public Member getMemberById(int memberId) {
        String sql = "SELECT * FROM member WHERE member_id = :memberId";
        SqlParameterSource paramSource = new MapSqlParameterSource("memberId", memberId);
        return jdbcTemplate.queryForObject(sql, paramSource, new MemberRowMapper());
    }

    // Update a member
    public void updateMember(Member member) {
        String sql = "UPDATE member SET user_id = :userId, member_status_id = :memberStatusId, score_detail_id = :scoreDetailId, name = :name, gender = :gender, " +
                "phone_number = :phoneNumber, place_of_birth = :placeOfBirth, date_of_birth = :dateOfBirth, address = :address WHERE member_id = :memberId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(member);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete a member
    public void deleteMember(int memberId) {
        String sql = "DELETE FROM member WHERE member_id = :memberId";
        SqlParameterSource paramSource = new MapSqlParameterSource("memberId", memberId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all members
    public List<Member> getAllMembers() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }
}
