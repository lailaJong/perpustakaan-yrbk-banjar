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
import tugasakhir.library.model.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class PublisherRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class PublisherRowMapper implements RowMapper<Publisher> {
        @Override
        public Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Publisher publisher = new Publisher();
            publisher.setPublisherId(rs.getString("publisher_id"));
            publisher.setPublisherName(rs.getString("publisher_name"));
            return publisher;
        }
    }

    // Add an publisher
    public void addPublisher(Publisher publisher) {
        try{
            log.info("[ADD PUBLISHER][{}]", applicationProperties.getINSERT_PUBLISHER());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(publisher);
            jdbcTemplate.update(applicationProperties.getINSERT_PUBLISHER(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get an publisher by ID
    public Publisher getPublisherById(String publisherId) {
        try{
            log.info("[GET PUBLISHER BY ID][{}][{}}]", publisherId, applicationProperties.getGET_PUBLISHER_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("publisherId", publisherId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_PUBLISHER_BY_ID(), paramSource, new PublisherRepository.PublisherRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public Publisher getPublisherByName(String publisherName) {
        try {
            publisherName = "%".concat(publisherName).concat("%");
            log.info("[GET PUBLISHER BY NAME][{}][{}]", publisherName, applicationProperties.getGET_PUBLISHER_BY_NAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("publisherName", publisherName);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_PUBLISHER_BY_NAME(), paramSource, new PublisherRowMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // Update an publisher
    public void updatePublisher(Publisher publisher) {
        try{
            log.info("[UPDATE PUBLISHER BY ID][{}][{}]", publisher.getPublisherId(), applicationProperties.getUPDATE_PUBLISHER_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(publisher);
            jdbcTemplate.update(applicationProperties.getUPDATE_PUBLISHER_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete an publisher
    public void deletePublisher(String publisherId) {
        try{
            log.info("[DELETE PUBLISHER BY ID][{}][{}]", publisherId, applicationProperties.getDELETE_PUBLISHER_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("publisherId", publisherId);
            jdbcTemplate.update(applicationProperties.getDELETE_PUBLISHER_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all publisher
    public List<Publisher> getAllPublishers() {
        try{
            log.info("[GET ALL PUBLISHERS][{}]", applicationProperties.getGET_ALL_PUBLISHER());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_PUBLISHER(), new PublisherRepository.PublisherRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public List<Publisher> getAllPublishersByName(String publisherName) {
        try {
            publisherName = "%".concat(publisherName).concat("%");
            log.info("[GET ALL PUBLISHERS BY NAME][{}][{}]", publisherName, applicationProperties.getGET_PUBLISHER_BY_NAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("publisherName", publisherName);
            return jdbcTemplate.query(applicationProperties.getGET_PUBLISHER_BY_NAME(), paramSource, new PublisherRowMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public String generatePublisherId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_PUBLISHER(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("PUB%03d", suffix);
    }
}
