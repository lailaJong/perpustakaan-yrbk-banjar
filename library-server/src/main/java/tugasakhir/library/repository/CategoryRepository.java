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
import tugasakhir.library.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class CategoryRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategoryId(rs.getString("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            return category;
        }
    }

    // Add a category
    public void addCategory(Category category) {
        try{
            log.info("[ADD CATEGORY][{}]", applicationProperties.getINSERT_AUTHOR());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(category);
            jdbcTemplate.update(applicationProperties.getINSERT_CATEGORY(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get a category by ID
    public Category getCategoryById(String categoryId) {
        try{
            log.info("[GET CATEGORY BY ID][{}][{}}]", categoryId, applicationProperties.getGET_CATEGORY_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("categoryId", categoryId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_CATEGORY_BY_ID(), paramSource, new CategoryRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }


    public Category getCategoryByName(String categoryName) {
        try {
            log.info("[GET CATEGORY BY NAME][{}][{}]", categoryName, applicationProperties.getGET_CATEGORY_BY_NAME());
            SqlParameterSource paramSource = new MapSqlParameterSource("categoryName", categoryName);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_CATEGORY_BY_NAME(), paramSource, new CategoryRowMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // Update a category
    public void updateCategory(Category category) {
        try{
            log.info("[UPDATE CATEGORY BY ID][{}][{}]", category.getCategoryId(), applicationProperties.getUPDATE_CATEGORY_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(category);
            jdbcTemplate.update(applicationProperties.getUPDATE_CATEGORY_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete a category
    public void deleteCategory(String categoryId) {
        try{
            log.info("[DELETE CATEGORY BY ID][{}][{}]", categoryId, applicationProperties.getDELETE_CATEGORY_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("categoryId", categoryId);
            jdbcTemplate.update(applicationProperties.getDELETE_CATEGORY_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all categories
    public List<Category> getAllCategories() {
        try{
            log.info("[GET ALL CATEGORY][{}]", applicationProperties.getGET_ALL_CATEGORY());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_CATEGORY(), new CategoryRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateCategoryId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_CATEGORY(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("CTG%03d", suffix);
    }
}
