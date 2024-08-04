package tugasakhir.library.utils.orderdetail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import tugasakhir.library.model.entity.Order;
import tugasakhir.library.model.request.orderdetail.OrderDetailRq;
import tugasakhir.library.model.request.orderdetail.UpdateOrderDetailRq;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class OrderDetailMapperImpl{

    public static Order toOrderDetail(OrderDetailRq orderDetailRq, String id, Date orderDate, Date takingDate, String status) {
        if (orderDetailRq == null) {
            return null;
        }
        Order orderDetail = new Order();
        orderDetail.setOrderId(id);
        orderDetail.setUserId(orderDetailRq.getUserId());
        orderDetail.setBookId(orderDetailRq.getBookId());
        orderDetail.setOrderDate(orderDate);
        orderDetail.setTakingDate(takingDate);
        orderDetail.setStatus(status);
        return orderDetail;
    }

    public static void updateOrderDetailFromUpdateOrderDetailRq(UpdateOrderDetailRq updateOrderDetailRq, Order orderDetail) throws ParseException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        mapper.setDateFormat(dateFormat);
        if ( updateOrderDetailRq == null ) {
            return;
        }

        if ( updateOrderDetailRq.getUserId() != null ) {
            orderDetail.setUserId( updateOrderDetailRq.getUserId() );
        }
        if ( updateOrderDetailRq.getBookId() != null ) {
            orderDetail.setBookId( updateOrderDetailRq.getBookId() );
        }
        if ( updateOrderDetailRq.getOrderDate() != null ) {
            orderDetail.setOrderDate(dateFormat.parse( updateOrderDetailRq.getOrderDate() ));
        }
        if ( updateOrderDetailRq.getTakingDate() != null ) {
            orderDetail.setTakingDate(dateFormat.parse( updateOrderDetailRq.getTakingDate() ));
        }
        if ( updateOrderDetailRq.getStatus() != null ) {
            orderDetail.setStatus( updateOrderDetailRq.getStatus() );
        }
    }
}