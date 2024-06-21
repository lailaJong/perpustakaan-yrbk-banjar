package tugasakhir.library.utils.orderdetail;

import org.springframework.core.annotation.Order;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.OrderDetail;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.orderdetail.OrderDetailRq;
import tugasakhir.library.model.request.orderdetail.UpdateOrderDetailRq;

import java.util.Date;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Override
    public OrderDetail toOrderDetail(OrderDetailRq orderDetailRq) {
        if (orderDetailRq == null) {
            return null;
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderDetailRq.getOrderId());
        orderDetail.setUserId(orderDetailRq.getUserId());
        orderDetail.setBookId(orderDetailRq.getBookId());
        orderDetail.setOrderDate(orderDetailRq.getOrderDate());
        orderDetail.setStatus(orderDetailRq.getStatus());
        return orderDetail;
    }

    @Override
    public void updateOrderDetailFromUpdateOrderDetailRq(UpdateOrderDetailRq updateOrderDetailRq, OrderDetail orderDetail) {
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
            orderDetail.setOrderDate( updateOrderDetailRq.getOrderDate() );
        }
        if ( updateOrderDetailRq.getStatus() != null ) {
            orderDetail.setStatus( updateOrderDetailRq.getStatus() );
        }
    }
}