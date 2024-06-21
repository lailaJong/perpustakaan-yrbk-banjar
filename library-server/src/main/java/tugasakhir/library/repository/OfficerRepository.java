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
import tugasakhir.library.model.entity.Officer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class OfficerRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class OfficerRowMapper implements RowMapper<Officer> {
        @Override
        public Officer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Officer officer = new Officer();
            officer.setOfficerId(rs.getString("officer_id"));
            officer.setUserId(rs.getString("user_id"));
            officer.setName(rs.getString("name"));
            return officer;
        }
    }

    // Add an officer
    public void addOfficer(Officer officer) {
        try{
            log.info("[ADD OFFICER][{}]", applicationProperties.getINSERT_OFFICER());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(officer);
            jdbcTemplate.update(applicationProperties.getINSERT_OFFICER(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get an officer by ID
    public Officer getOfficerById(String officerId) {
        try{
            log.info("[GET OFFICER BY ID][{}][{}}]", officerId, applicationProperties.getGET_OFFICER_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("officerId", officerId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_OFFICER_BY_ID(), paramSource, new OfficerRepository.OfficerRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update an officer
    public void updateOfficer(Officer officer) {
        try{
            log.info("[UPDATE OFFICER BY ID][{}][{}]", officer.getOfficerId(), applicationProperties.getUPDATE_OFFICER_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(officer);
            jdbcTemplate.update(applicationProperties.getUPDATE_OFFICER_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete an officer
    public void deleteOfficer(String officerId) {
        try{
            log.info("[DELETE OFFICER BY ID][{}][{}]", officerId, applicationProperties.getDELETE_OFFICER_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("officerId", officerId);
            jdbcTemplate.update(applicationProperties.getDELETE_OFFICER_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all officer
    public List<Officer> getAllOfficers() {
        try{
            log.info("[GET ALL OFFICER][{}]", applicationProperties.getGET_ALL_OFFICER());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_OFFICER(), new OfficerRepository.OfficerRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateOfficerId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_OFFICER(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("OFC%03d", suffix);
    }
}
