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
import tugasakhir.library.model.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
public class OrderDetailRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class OrderDetailRowMapper implements RowMapper<OrderDetail> {
        @Override
        public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(rs.getString("order_id"));
            orderDetail.setUserId(rs.getString("user_id"));
            orderDetail.setBookId(rs.getString("book_id"));
            orderDetail.setOrderDate(rs.getDate("order_date"));
            orderDetail.setStatus(rs.getString("status"));
            return orderDetail;
        }
    }

    // Add an order detail
    public void addOrderDetail(OrderDetail orderDetail) {
        try{
            log.info("[ADD ORDER DETAIL][{}]", applicationProperties.getINSERT_ORDER_DETAIL());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(orderDetail);
            jdbcTemplate.update(applicationProperties.getINSERT_ORDER_DETAIL(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get an order detail
    public OrderDetail getOrderDetailById(String orderId) {
        try{
            log.info("[GET ORDER DETAIL BY ID][{}][{}}]", orderId, applicationProperties.getGET_ORDER_DETAIL_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("orderId", orderId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_ORDER_DETAIL_BY_ID(), paramSource, new OrderDetailRepository.OrderDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public int getCountOrderDetailByUserId(String userId, String orderStatus) {
        try{
            log.info("[GET COUNT ORDER DETAIL BY USER ID][{}][{}}]", userId, applicationProperties.getGET_COUNT_ORDER_DETAIL_BY_USER_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("userId", userId)
                    .addValue("orderStatus", orderStatus);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ORDER_DETAIL_BY_USER_ID(), paramSource, Integer.class);
        }catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }
    }

    // Update an order detail
    public void updateOrderDetail(OrderDetail orderDetail) {
        try{
            log.info("[UPDATE ORDER DETAIL BY ID][{}][{}]", orderDetail.getOrderId(), applicationProperties.getUPDATE_ORDER_DETAIL_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(orderDetail);
            jdbcTemplate.update(applicationProperties.getUPDATE_ORDER_DETAIL_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete an order detail
    public void deleteOrderDetail(String orderId) {
        try{
            log.info("[DELETE ORDER DETAIL BY ID][{}][{}]", orderId, applicationProperties.getDELETE_ORDER_DETAIL_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("orderId", orderId);
            jdbcTemplate.update(applicationProperties.getDELETE_ORDER_DETAIL_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all order details
    public List<OrderDetail> getAllOrderDetails() {
        try{
            log.info("[GET ALL ORDER DETAIL][{}]", applicationProperties.getGET_ALL_ORDER_DETAIL());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_ORDER_DETAIL(), new OrderDetailRepository.OrderDetailRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public String generateOrderDetailId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_ORDER_DETAIL(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("ORD%03d", suffix);
    }
}
