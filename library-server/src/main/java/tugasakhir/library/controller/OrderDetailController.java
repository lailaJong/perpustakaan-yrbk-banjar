package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.dto.OrderDetail;
import tugasakhir.library.model.dto.OrderDetailOfficer;
import tugasakhir.library.model.entity.Order;
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
        ResponseInfo<List<Order>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL ORDER DETAILS][{}]", requestId);
        responseInfo = orderDetailUsecase.getAllOrderDetails();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/allOrder")
    ResponseEntity<Object> getAllOrderDetailsOfficer(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<OrderDetailOfficer>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL ORDER DETAILS OFFICER][{}]", requestId);
        responseInfo = orderDetailUsecase.getAllOrderDetailsOfficer();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/all/userId")
    ResponseEntity<Object> getAllOrderDetailsByUserId(@RequestHeader(value = "request-id", required = false) String requestId,
                                                      @RequestParam(value = "userId") String userId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<OrderDetail>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL ORDER DETAILS BY USER ID][{}][{}]", userId, requestId);
        responseInfo = orderDetailUsecase.getAllOrderDetailsByUserId(userId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }


    @GetMapping("/all/userId/bookTitle")
    ResponseEntity<Object> getAllOrderDetailsByUserIdAndBookTitle(@RequestHeader(value = "request-id", required = false) String requestId,
                                                                  @RequestParam(value = "userId") String userId,
                                                                  @RequestParam(value = "bookTitle") String bookTitle) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<OrderDetail>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL ORDER DETAILS BY USER ID AND BOOK TITLE][{}][{}][{}]", userId, bookTitle, requestId);
        responseInfo = orderDetailUsecase.getAllOrderDetailsByUserIdAndBookTitle(userId, bookTitle);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getOrderDetailById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "orderId") String orderId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Order> responseInfo;
        log.info("[REQUEST RECEIVED - GET ORDER DETAIL BY ID][{}][{}]", orderId, requestId);
        responseInfo = orderDetailUsecase.getOrderDetailById(orderId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get count order detail by user id for jumlah koleksi dipesan in dashboard member
    @GetMapping("/id")
    ResponseEntity<Object> getCountOrderDetailByUserId(@RequestHeader(value = "request-id", required = false) String requestId,
                                              @RequestParam(value = "userId") String userId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Integer> responseInfo;
        log.info("[REQUEST RECEIVED - GET COUNT ORDER DETAIL BY USER ID][{}][{}]", userId, requestId);
        responseInfo = orderDetailUsecase.getCountOrderDetailByUserId(userId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createOrderDetail(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid OrderDetailRq orderDetailRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW ORDER DETAIL][{}][PAYLOAD: {}]", requestId, orderDetailRq);
        ResponseInfo<Order> responseInfo = orderDetailUsecase.addNewOrderDetail(orderDetailRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //update status dibatalkan
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
