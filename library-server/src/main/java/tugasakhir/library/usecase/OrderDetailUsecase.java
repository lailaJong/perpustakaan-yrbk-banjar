package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.config.properties.ApplicationProperties;
import tugasakhir.library.model.entity.OrderDetail;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.orderdetail.OrderDetailRq;
import tugasakhir.library.model.request.orderdetail.UpdateOrderDetailRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.OrderDetailRepository;
import tugasakhir.library.utils.orderdetail.OrderDetailMapper;

import java.util.List;

@Component
@Slf4j
public class OrderDetailUsecase {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ApplicationProperties applicationProperties;

    public ResponseInfo<List<OrderDetail>> getAllOrderDetails() {
        ResponseInfo<List<OrderDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<OrderDetail> orderDetails;
            orderDetails = orderDetailRepository.getAllOrderDetails();
            orderDetails.addAll(orderDetailRepository.getAllOrderDetails());
            responseInfo.setSuccess(orderDetails);
            log.info("[{}][SUCCESS GET ALL ORDER DETAIL][DATA SIZE: {}]", getClass().getSimpleName(), orderDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL ORDER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<OrderDetail> getOrderDetailById(String orderId) {
        ResponseInfo<OrderDetail> responseInfo = new ResponseInfo<>();

        try {
            OrderDetail orderDetail;
            orderDetail = orderDetailRepository.getOrderDetailById(orderId);
            responseInfo.setSuccess(orderDetail);
            log.info("[{}][SUCCESS GET ORDER DETAIL][ID: {}]", getClass().getSimpleName(), orderId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ORDER DETAIL][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), orderId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Integer> getCountOrderDetailByUserId(String userId) {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            String orderStatus = applicationProperties.getOrderedStatus();
            count = orderDetailRepository.getCountOrderDetailByUserId(userId, orderStatus);
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT ORDER DETAIL][USER ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT ORDER DETAIL][USER ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<OrderDetail> addNewOrderDetail(OrderDetailRq orderDetailRq) {
        ResponseInfo<OrderDetail> responseInfo = new ResponseInfo<>();

        try {
            OrderDetail orderDetail;
            orderDetailRq.setOrderId(orderDetailRepository.generateOrderDetailId());
            orderDetail = OrderDetailMapper.INSTANCE.toOrderDetail(orderDetailRq);
            orderDetailRepository.addOrderDetail(orderDetail);
            responseInfo.setSuccess(orderDetail);
            log.info("[{}][SUCCESS ADD NEW ORDER DETAIL]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW ORDER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateOrderDetail(UpdateOrderDetailRq updateOrderDetailRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            OrderDetail orderDetail = orderDetailRepository.getOrderDetailById(updateOrderDetailRq.getOrderId());
            if (orderDetail != null) {
                OrderDetailMapper.INSTANCE.updateOrderDetailFromUpdateOrderDetailRq(updateOrderDetailRq, orderDetail);
                orderDetailRepository.updateOrderDetail(orderDetail);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE ORDER DETAIL]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE ORDER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteOrderDetail(String orderId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            orderDetailRepository.deleteOrderDetail(orderId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE ORDER DETAIL][{}]", getClass().getSimpleName(), orderId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE ORDER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
