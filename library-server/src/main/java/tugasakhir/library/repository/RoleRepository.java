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
import tugasakhir.library.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class RoleRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_JDBC_POSTGRES)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(ApplicationConstant.PROPERTIES_NAME)
    protected ApplicationProperties applicationProperties;

    private static final class RoleRowMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setRoleId(rs.getString("role_id"));
            role.setRoleName(rs.getString("role_name"));
            return role;
        }
    }

    // Add an role
    public void addRole(Role role) {
        try{
            log.info("[ADD ROLE][{}]", applicationProperties.getINSERT_ROLE());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(role);
            jdbcTemplate.update(applicationProperties.getINSERT_ROLE(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get an role by ID
    public Role getRoleById(String roleId) {
        try{
            log.info("[GET ROLE BY ID][{}][{}}]", roleId, applicationProperties.getGET_ROLE_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("roleId", roleId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_ROLE_BY_ID(), paramSource, new RoleRepository.RoleRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public Role getRoleByName(String roleName) {
        try{
            log.info("[GET ROLE BY ID][{}][{}}]", roleName, applicationProperties.getGET_ROLE_BY_NAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("roleName", roleName);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_ROLE_BY_NAME(), paramSource, new RoleRepository.RoleRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update an role
    public void updateRole(Role role) {
        try{
            log.info("[UPDATE ROLE BY ID][{}][{}]", role.getRoleId(), applicationProperties.getUPDATE_ROLE_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(role);
            jdbcTemplate.update(applicationProperties.getUPDATE_ROLE_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete an role
    public void deleteRole(String roleId) {
        try{
            log.info("[DELETE ROLE BY ID][{}][{}]", roleId, applicationProperties.getDELETE_ROLE_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("roleId", roleId);
            jdbcTemplate.update(applicationProperties.getDELETE_ROLE_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all roles
    public List<Role> getAllRoles() {
        try{
            log.info("[GET ALL ROLE][{}]", applicationProperties.getGET_ALL_ROLE());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_ROLE(), new RoleRepository.RoleRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateRoleId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_ROLE(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("ROL%03d", suffix);
    }
}
