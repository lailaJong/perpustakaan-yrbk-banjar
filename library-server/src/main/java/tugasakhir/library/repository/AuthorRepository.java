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
import tugasakhir.library.model.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class AuthorRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    private static final class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setAuthorId(rs.getInt("author_id"));
            author.setAuthorName(rs.getString("author_name"));
            return author;
        }
    }

    // Add an author
    public void addAuthor(Author author) {
        String sql = "INSERT INTO author (author_id, author_name) VALUES (:authorId, :authorName)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(author);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get an author by ID
    public Author getAuthorById(int authorId) {
        String sql = "SELECT * FROM author WHERE author_id = :authorId";
        SqlParameterSource paramSource = new MapSqlParameterSource("authorId", authorId);
        return jdbcTemplate.queryForObject(sql, paramSource, new AuthorRowMapper());
    }

    // Get an author by name
    public Author getAuthorByName(String authorName) {
        String sql = "SELECT * FROM author WHERE author_name = :authorName";
        SqlParameterSource paramSource = new MapSqlParameterSource("authorName", authorName);
        return jdbcTemplate.queryForObject(sql, paramSource, new AuthorRowMapper());
    }

    // Update an author
    public void updateAuthor(Author author) {
        String sql = "UPDATE author SET author_name = :authorName WHERE author_id = :authorId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(author);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete an author
    public void deleteAuthor(int authorId) {
        String sql = "DELETE FROM author WHERE author_id = :authorId";
        SqlParameterSource paramSource = new MapSqlParameterSource("authorId", authorId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all authors
    public List<Author> getAllAuthors() {
        String sql = "SELECT * FROM author";
        return jdbcTemplate.query(sql, new AuthorRowMapper());
    }
}
