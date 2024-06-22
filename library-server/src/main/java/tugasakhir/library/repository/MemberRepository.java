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
import tugasakhir.library.model.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class MemberRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setUserId(rs.getString("user_id"));
            member.setMemberStatusId(rs.getString("member_status_id"));
            member.setScoreDetailId(rs.getString("score_detail_id"));
            member.setName(rs.getString("name"));
            member.setGender(rs.getString("gender"));
            member.setPhoneNumber(rs.getString("phone_number"));
            member.setPlaceOfBirth(rs.getString("place_of_birth"));
            member.setDateOfBirth(rs.getDate("date_of_birth"));
            member.setAddress(rs.getString("address"));
            member.setPoint(rs.getInt("point"));
            member.setRegristrationDate(rs.getDate("registration_date"));
            return member;
        }
    }

    // Add a member
    public void addMember(Member member) {
        try{
            log.info("[ADD MEMBER][{}]", applicationProperties.getINSERT_MEMBER());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(member);
            jdbcTemplate.update(applicationProperties.getINSERT_MEMBER(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get a member by ID
    public Member getMemberById(String memberId) {
        try{
            log.info("[GET MEMBER BY ID][{}][{}]", memberId, applicationProperties.getGET_MEMBER_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("memberId", memberId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_MEMBER_BY_ID(), paramSource, new MemberRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public List<Member> getMembersByStatusId(String statusId) {
        try {
            log.info("[GET MEMBER BY STATUS ID][{}][{}]", statusId, applicationProperties.getGET_MEMBER_BY_STATUS_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("statusId", statusId);
            return jdbcTemplate.query(applicationProperties.getGET_MEMBER_BY_STATUS_ID(), paramSource, new MemberRowMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public Member getMemberByUserId(String userId) {
        try{
            log.info("[GET MEMBER BY USER ID][{}][{}]", userId, applicationProperties.getGET_MEMBER_BY_USER_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("userId", userId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_MEMBER_BY_USER_ID(), paramSource, new MemberRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }


    public Member getMemberByName(String memberName) {
        try{
            log.info("[GET MEMBER BY NAME][{}][{}]", memberName, applicationProperties.getGET_MEMBER_BY_NAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("memberName", memberName);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_MEMBER_BY_NAME(), paramSource, new MemberRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update a member
    public void updateMember(Member member) {
        try{
            log.info("[UPDATE MEMBER BY ID][{}][{}]", member.getMemberId(), applicationProperties.getUPDATE_MEMBER_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(member);
            jdbcTemplate.update(applicationProperties.getUPDATE_MEMBER_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    // Delete a member
    public void deleteMember(String memberId) {
        try{
            log.info("[DELETE MEMBER BY ID][{}][{}]", memberId, applicationProperties.getDELETE_MEMBER_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("memberId", memberId);
            jdbcTemplate.update(applicationProperties.getDELETE_MEMBER_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all members
    public List<Member> getAllMembers() {
        try{
            log.info("[GET ALL MEMBER][{}]", applicationProperties.getGET_ALL_MEMBER());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_MEMBER(), new MemberRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateMemberId() {
        try{
            log.info("[GENERATE MEMBER ID][{}]", applicationProperties.getGET_COUNT_ALL_MEMBER());
            int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_MEMBER(), (SqlParameterSource) null, Integer.class);
            int suffix = count + 1;
            return String.format("MBR%03d", suffix);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
