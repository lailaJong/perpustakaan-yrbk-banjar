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
import tugasakhir.library.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
public class CategoryRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;
    private static final class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            return category;
        }
    }

    // Add a category
    public void addCategory(Category category) {
        String sql = "INSERT INTO category (category_id, category_name) VALUES (:categoryId, :categoryName)";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(category);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get a category by ID
    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM category WHERE category_id = :categoryId";
        SqlParameterSource paramSource = new MapSqlParameterSource("categoryId", categoryId);
        return jdbcTemplate.queryForObject(sql, paramSource, new CategoryRowMapper());
    }

    // Update a category
    public void updateCategory(Category category) {
        String sql = "UPDATE category SET category_name = :categoryName WHERE category_id = :categoryId";

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(category);
        jdbcTemplate.update(sql, paramSource);
    }

    // Delete a category
    public void deleteCategory(int categoryId) {
        String sql = "DELETE FROM category WHERE category_id = :categoryId";
        SqlParameterSource paramSource = new MapSqlParameterSource("categoryId", categoryId);
        jdbcTemplate.update(sql, paramSource);
    }

    // Get all categories
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }
}
