package tugasakhir.library.utils.orderdetail;

import tugasakhir.library.model.entity.Order;
import tugasakhir.library.model.request.orderdetail.OrderDetailRq;
import tugasakhir.library.model.request.orderdetail.UpdateOrderDetailRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class OrderDetailMapperImpl{

    public static Order toOrderDetail(OrderDetailRq orderDetailRq, String id) {
        if (orderDetailRq == null) {
            return null;
        }
        Order orderDetail = new Order();
        orderDetail.setOrderId(id);
        orderDetail.setUserId(orderDetailRq.getUserId());
        orderDetail.setBookId(orderDetailRq.getBookId());
        orderDetail.setOrderDate(orderDetailRq.getOrderDate());
        orderDetail.setTakingDate(orderDetailRq.getTakingDate());
        orderDetail.setStatus(orderDetailRq.getStatus());
        return orderDetail;
    }

    public static void updateOrderDetailFromUpdateOrderDetailRq(UpdateOrderDetailRq updateOrderDetailRq, Order orderDetail) {
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
        if ( updateOrderDetailRq.getTakingDate() != null ) {
            orderDetail.setTakingDate( updateOrderDetailRq.getTakingDate() );
        }
        if ( updateOrderDetailRq.getStatus() != null ) {
            orderDetail.setStatus( updateOrderDetailRq.getStatus() );
        }
    }
}