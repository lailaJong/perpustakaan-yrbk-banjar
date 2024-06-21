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
import tugasakhir.library.model.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class AuthorRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setAuthorId(rs.getString("author_id"));
            author.setAuthorName(rs.getString("author_name"));
            return author;
        }
    }

    // Add an author
    public void addAuthor(Author author) {
        try{
            log.info("[ADD AUTHOR][{}]", applicationProperties.getINSERT_AUTHOR());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(author);
            jdbcTemplate.update(applicationProperties.getINSERT_AUTHOR(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get an author by ID
    public Author getAuthorById(String authorId) {
        try{
            log.info("[GET AUTHOR BY ID][{}][{}}]", authorId, applicationProperties.getGET_AUTHOR_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("authorId", authorId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_AUTHOR_BY_ID(), paramSource, new AuthorRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Get an author by name
    public Author getAuthorByName(String authorName) {
        try {
            log.info("[GET AUTHOR BY NAME][{}][{}]", authorName, applicationProperties.getGET_AUTHOR_BY_NAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("authorName", authorName);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_AUTHOR_BY_NAME(), paramSource, new AuthorRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update an author
    public void updateAuthor(Author author) {
        try{
            log.info("[UPDATE AUTHOR BY ID][{}][{}]", author.getAuthorId(), applicationProperties.getUPDATE_AUTHOR_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(author);
            jdbcTemplate.update(applicationProperties.getUPDATE_AUTHOR_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete an author
    public void deleteAuthor(String authorId) {
        try{
            log.info("[DELETE AUTHOR BY ID][{}][{}]", authorId, applicationProperties.getDELETE_AUTHOR_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("authorId", authorId);
            jdbcTemplate.update(applicationProperties.getDELETE_AUTHOR_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all authors
    public List<Author> getAllAuthors() {
        try{
            log.info("[GET ALL AUTHOR][{}]", applicationProperties.getGET_ALL_AUTHOR());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_AUTHOR(), new AuthorRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateAuthorId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_AUTHOR(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("AUT%03d", suffix);
    }
}


