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
import tugasakhir.library.model.entity.ScoreDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class ScoreDetailRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class ScoreDetailRowMapper implements RowMapper<ScoreDetail> {
        @Override
        public ScoreDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            ScoreDetail scoreDetail = new ScoreDetail();
            scoreDetail.setScoreDetailId(rs.getString("score_detail_id"));
            scoreDetail.setExtraBorrowTime(rs.getInt("extra_borrow_time"));
            scoreDetail.setExtraBooksQuota(rs.getInt("extra_books_quota"));
            return scoreDetail;
        }
    }

    // Add an scoreDetail
    public void addScoreDetail(ScoreDetail scoreDetail) {
        try{
            log.info("[ADD SCORE_DETAIL][{}]", applicationProperties.getINSERT_SCORE_DETAIL());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(scoreDetail);
            jdbcTemplate.update(applicationProperties.getINSERT_SCORE_DETAIL(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get an scoreDetail by ID
    public ScoreDetail getScoreDetailById(String scoreDetailId) {
        try{
            log.info("[GET SCORE_DETAIL BY ID][{}][{}}]", scoreDetailId, applicationProperties.getGET_SCORE_DETAIL_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("scoreDetailId", scoreDetailId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_SCORE_DETAIL_BY_ID(), paramSource, new ScoreDetailRepository.ScoreDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update an scoreDetail
    public void updateScoreDetail(ScoreDetail scoreDetail) {
        try{
            log.info("[UPDATE SCORE_DETAIL BY ID][{}][{}]", scoreDetail.getScoreDetailId(), applicationProperties.getUPDATE_SCORE_DETAIL_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(scoreDetail);
            jdbcTemplate.update(applicationProperties.getUPDATE_SCORE_DETAIL_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete an scoreDetail
    public void deleteScoreDetail(String scoreDetailId) {
        try{
            log.info("[DELETE SCORE_DETAIL BY ID][{}][{}]", scoreDetailId, applicationProperties.getDELETE_SCORE_DETAIL_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("scoreDetailId", scoreDetailId);
            jdbcTemplate.update(applicationProperties.getDELETE_SCORE_DETAIL_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all scoreDetails
    public List<ScoreDetail> getAllScoreDetails() {
        try{
            log.info("[GET ALL SCORE_DETAIL][{}]", applicationProperties.getGET_ALL_SCORE_DETAIL());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_SCORE_DETAIL(), new ScoreDetailRepository.ScoreDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateScoreDetailId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_SCORE_DETAIL(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("SCO%03d", suffix);
    }
}
