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
import tugasakhir.library.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class UserRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_JDBC_POSTGRES)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(ApplicationConstant.PROPERTIES_NAME)
    protected ApplicationProperties applicationProperties;

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setRoleId(rs.getString("role_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    // Add an user
    public void addUser(User user) {
        try{
            log.info("[ADD USER][{}]", applicationProperties.getINSERT_USER());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
            jdbcTemplate.update(applicationProperties.getINSERT_USER(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get an user by ID
    public User getUserById(String userId) {
        try{
            log.info("[GET USER BY ID][{}][{}}]", userId, applicationProperties.getGET_USER_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("userId", userId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_USER_BY_ID(), paramSource, new UserRepository.UserRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try{
            log.info("[GET USER BY USERNAME][{}][{}}]", username, applicationProperties.getGET_USER_BY_USERNAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("username", username);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_USER_BY_USERNAME(), paramSource, new UserRepository.UserRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update an user
    public void updateUser(User user) {
        try{
            log.info("[UPDATE USER BY ID][{}][{}]", user.getUserId(), applicationProperties.getUPDATE_USER_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
            jdbcTemplate.update(applicationProperties.getUPDATE_USER_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete an user
    public void deleteUser(String userId) {
        try{
            log.info("[DELETE USER BY ID][{}][{}]", userId, applicationProperties.getDELETE_USER_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("userId", userId);
            jdbcTemplate.update(applicationProperties.getDELETE_USER_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        try{
            log.info("[GET ALL USER][{}]", applicationProperties.getGET_ALL_USER());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_USER(), new UserRepository.UserRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateUserId() {
        try{
            log.info("[GENERATE USER ID][{}]", applicationProperties.getGET_COUNT_ALL_USER());
            int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_USER(), (SqlParameterSource) null, Integer.class);
            int suffix = count + 1;
            return String.format("USR%03d", suffix);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean existsByUsername(String username) {
        try{
            log.info("[GET EXIST USERNAME][{}][{}]", username, applicationProperties.getGET_EXIST_USERNAME());
            Map<String, Object> params = new HashMap<>();
            params.put("username", username);

            int count = jdbcTemplate.queryForObject(applicationProperties.getGET_EXIST_USERNAME(), params, Integer.class);
            return count > 0;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
