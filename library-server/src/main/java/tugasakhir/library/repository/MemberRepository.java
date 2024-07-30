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
import tugasakhir.library.model.dto.ListMember;
import tugasakhir.library.model.dto.TopBorrowerMember;
import tugasakhir.library.model.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class MemberRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_JDBC_POSTGRES)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(ApplicationConstant.PROPERTIES_NAME)
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
            member.setRegistrationDate(rs.getDate("registration_date"));
            return member;
        }
    }

    private static final class TopMemberRowMapper implements RowMapper<TopBorrowerMember> {
        @Override
        public TopBorrowerMember mapRow(ResultSet rs, int rowNum) throws SQLException {
            TopBorrowerMember member = new TopBorrowerMember();
            member.setName(rs.getString("name"));
            member.setTotalBorrowings(rs.getLong("total_borrowings"));
            return member;
        }
    }

    private static final class MemberNamesRowMapper implements RowMapper<ListMember> {
        @Override
        public ListMember mapRow(ResultSet rs, int rowNum) throws SQLException {
            ListMember member = new ListMember();
            member.setMemberId(rs.getString("member_id"));
            member.setName(rs.getString("name"));
            return member;
        }
    }

    // Add a member
    public void addMember(Member member) {
        try{
            log.info("[ADD MEMBER][{}][{}]", applicationProperties.getINSERT_MEMBER(), member);
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(member);
            jdbcTemplate.update(applicationProperties.getINSERT_MEMBER(), paramSource);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
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


    public Member getMemberByName(String memberName) throws RuntimeException {
        try{
            log.info("[GET MEMBER BY NAME][{}][{}]", memberName, applicationProperties.getGET_MEMBER_BY_NAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("memberName", memberName);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_MEMBER_BY_NAME(), paramSource, new MemberRowMapper());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
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

    // Get all members
    public List<ListMember> getAllMemberNames() {
        try{
            log.info("[GET ALL MEMBER NAMES][{}]", applicationProperties.getGET_ALL_MEMBER_NAMES());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_MEMBER_NAMES(), new MemberNamesRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    // Get all members
    public List<Member> getAllMembers() {
        try{
            log.info("[GET ALL MEMBER][{}]", applicationProperties.getGET_ALL_MEMBER());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_MEMBER(), new MemberRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    // Get all members
    public List<Member> getAllMembersByName(String name) {
        try{
            name = "%".concat(name).concat("%");
            log.info("[GET ALL MEMBER][{}]", applicationProperties.getGET_ALL_MEMBER_BY_NAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("name", name);
            return jdbcTemplate.query(applicationProperties.getGET_ALL_MEMBER_BY_NAME(), paramSource, new MemberRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public String generateMemberId() throws RuntimeException {
        try{
            log.info("[GENERATE MEMBER ID][{}]", applicationProperties.getGET_COUNT_ALL_MEMBER());
            int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_MEMBER(), (SqlParameterSource) null, Integer.class);
            int suffix = count + 1;
            return String.format("MBR%03d", suffix);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public int getCountAllMember() {
        try{
            log.info("[GET COUNT ALL MEMBER][{}]", applicationProperties.getGET_COUNT_ALL_MEMBER());
            return jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_MEMBER(), (SqlParameterSource) null, Integer.class);
        }catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }
    }

    // Get top borrower member
    public List<TopBorrowerMember> getTopBorrowerMember() {
        try {
            log.info("[GET TOP BORROWER MEMBER > 1][{}]", applicationProperties.getGET_TOP_BORROWER_MEMBER());
            return jdbcTemplate.query(applicationProperties.getGET_TOP_BORROWER_MEMBER(), new TopMemberRowMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    //get scoreId
    public String getScoreId(String userId) {
        try{
            log.info("[GET SCORE ID MEMBER][{}]", applicationProperties.getGET_SCORE_ID_MEMBER());
            SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_SCORE_ID_MEMBER(), parameterSource, String.class);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean existsByMemberName(String name) {
        try{
            log.info("[CHECK MEMBER NAME IS EXIST OR NOT][{}][{}]", applicationProperties.getGET_EXIST_MEMBER_NAME(), name);
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            int count = jdbcTemplate.queryForObject(applicationProperties.getGET_EXIST_MEMBER_NAME(), params, Integer.class);
            log.info("[COUNT: {}]", count);
            return count > 0;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    //get point
//    public int getMemberPoint(String userId) {
//        try{
//            log.info("[GET MEMBER POINT][{}]", applicationProperties.getGET_MEMBER_POINT());
//            SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);
//            return jdbcTemplate.queryForObject(applicationProperties.getGET_MEMBER_POINT(), parameterSource, Integer.class);
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return 0;
//        }
//    }

    // Delete a member
//    public void deleteMember(String memberId) {
//        try{
//            log.info("[DELETE MEMBER BY ID][{}][{}]", memberId, applicationProperties.getDELETE_MEMBER_BY_ID());
//            SqlParameterSource paramSource = new MapSqlParameterSource("memberId", memberId);
//            jdbcTemplate.update(applicationProperties.getDELETE_MEMBER_BY_ID(), paramSource);
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//    }

//    public List<Member> getMembersByStatusId(String statusId) {
//        try {
//            log.info("[GET MEMBER BY STATUS ID][{}][{}]", statusId, applicationProperties.getGET_MEMBER_BY_STATUS_ID());
//            SqlParameterSource paramSource = new MapSqlParameterSource("statusId", statusId);
//            return jdbcTemplate.query(applicationProperties.getGET_MEMBER_BY_STATUS_ID(), paramSource, new MemberRowMapper());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return Collections.emptyList();
//        }
//    }
}
