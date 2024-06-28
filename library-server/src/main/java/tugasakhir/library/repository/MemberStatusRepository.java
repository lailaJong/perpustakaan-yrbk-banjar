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
import tugasakhir.library.model.entity.MemberStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class MemberStatusRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_JDBC_POSTGRES)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(ApplicationConstant.PROPERTIES_NAME)
    protected ApplicationProperties applicationProperties;

    private static final class MemberStatusRowMapper implements RowMapper<MemberStatus> {
        @Override
        public MemberStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
            MemberStatus memberStatus = new MemberStatus();
            memberStatus.setMemberStatusId(rs.getString("member_status_id"));
            memberStatus.setStatus(rs.getString("status"));
            return memberStatus;
        }
    }

    // Add a member status
    public void addMemberStatus(MemberStatus memberStatus) {
        try{
            log.info("[ADD MEMBER STATUS][{}]", applicationProperties.getINSERT_MEMBER_STATUS());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberStatus);
            jdbcTemplate.update(applicationProperties.getINSERT_MEMBER_STATUS(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get a member status by ID
    public MemberStatus getMemberStatusById(String memberStatusId) {
        try{
            log.info("[GET MEMBER STATUS BY ID][{}][{}}]", memberStatusId, applicationProperties.getGET_MEMBER_STATUS_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("memberStatusId", memberStatusId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_MEMBER_STATUS_BY_ID(), paramSource, new MemberStatusRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String getStatusByMemberStatusId(String memberStatusId) {
        try{
            log.info("[GET STATUS BY MEMBER STATUS ID][{}][{}}]", memberStatusId, applicationProperties.getGET_STATUS_BY_MEMBER_STATUS_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("memberStatusId", memberStatusId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_STATUS_BY_MEMBER_STATUS_ID(), paramSource, String.class);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public MemberStatus getMemberStatusByStatus(String status) {
        try{
            log.info("[GET MEMBER STATUS BY STATUS][{}][{}}]", status, applicationProperties.getGET_MEMBER_STATUS_BY_STATUS());
            SqlParameterSource paramSource = new MapSqlParameterSource("status", status);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_MEMBER_STATUS_BY_STATUS(), paramSource, new MemberStatusRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update a member status
    public void updateMemberStatus(MemberStatus memberStatus) {
        try{
            log.info("[UPDATE MEMBER STATUS BY ID][{}][{}]", memberStatus.getMemberStatusId(), applicationProperties.getUPDATE_MEMBER_STATUS_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberStatus);
            jdbcTemplate.update(applicationProperties.getUPDATE_MEMBER_STATUS_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete a member status
    public void deleteMemberStatus(String memberStatusId) {
        try{
            log.info("[DELETE MEMBER STATUS BY ID][{}][{}]", memberStatusId, applicationProperties.getDELETE_MEMBER_STATUS_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("memberStatusId", memberStatusId);
            jdbcTemplate.update(applicationProperties.getDELETE_MEMBER_STATUS_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all member statuses
    public List<MemberStatus> getAllMemberStatuses() {
        try{
            log.info("[GET ALL MEMBER STATUS][{}]", applicationProperties.getGET_ALL_MEMBER_STATUS());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_MEMBER_STATUS(), new MemberStatusRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateMemberStatusId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_MEMBER_STATUS(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("MST%03d", suffix);
    }
}
