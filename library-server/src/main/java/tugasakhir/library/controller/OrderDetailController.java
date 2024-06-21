package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.entity.OrderDetail;
import tugasakhir.library.model.request.orderdetail.OrderDetailRq;
import tugasakhir.library.model.request.orderdetail.UpdateOrderDetailRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.OrderDetailUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order/details")
@Slf4j
public class OrderDetailController {

    @Autowired
    private OrderDetailUsecase orderDetailUsecase;

    @GetMapping("/all")
    ResponseEntity<Object> getAllOrderDetails(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<OrderDetail>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL ORDER DETAILS][{}]", requestId);
        responseInfo = orderDetailUsecase.getAllOrderDetails();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getOrderDetailById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "orderId") String orderId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<OrderDetail> responseInfo;
        log.info("[REQUEST RECEIVED - GET ORDER DETAIL BY ID][{}][{}]", orderId, requestId);
        responseInfo = orderDetailUsecase.getOrderDetailyId(orderId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createOrderDetail(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid OrderDetailRq orderDetailRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW ORDER DETAIL][{}][PAYLOAD: {}]", requestId, orderDetailRq);
        ResponseInfo<OrderDetail> responseInfo = orderDetailUsecase.addNewOrderDetail(orderDetailRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateOrderDetail(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateOrderDetailRq updateOrderDetailRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE ORDER DETAIL][{}][PAYLOAD: {}]", requestId, updateOrderDetailRq);
        ResponseInfo<Object> responseInfo = orderDetailUsecase.updateOrderDetail(updateOrderDetailRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteOrderDetail(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "orderId") String orderId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE ORDER DETAIL][{}][ORDER ID: {}]", requestId, orderId);
        ResponseInfo<Object> responseInfo = orderDetailUsecase.deleteOrderDetail(orderId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
