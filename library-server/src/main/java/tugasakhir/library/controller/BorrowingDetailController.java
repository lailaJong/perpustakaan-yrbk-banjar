package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.dto.BorrowingDetail;
import tugasakhir.library.model.dto.BorrowingHistories;
import tugasakhir.library.model.dto.BorrowingHistoriesUser;
import tugasakhir.library.model.entity.Borrowing;
import tugasakhir.library.model.request.borrowingdetail.BorrowingDetailRq;
import tugasakhir.library.model.request.borrowingdetail.UpdateBorrowingDetailRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.BorrowingDetailUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/borrowing/details")
@Slf4j
public class BorrowingDetailController {

    @Autowired
    private BorrowingDetailUsecase borrowingDetailUsecase;

    @GetMapping("/all")
    ResponseEntity<Object> getAllBorrowingDetails(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<Borrowing>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BORROWING DETAILS][{}]", requestId);
        responseInfo = borrowingDetailUsecase.getAllBorrowingDetails();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get all borrowing with status "dipinjam" by user id
    @GetMapping("/all/userId")
    ResponseEntity<Object> getAllBorrowingDetailsByUserId(@RequestHeader(value = "request-id", required = false) String requestId,
                                                          @RequestParam(value = "userId") String userId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BorrowingDetail>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BORROWING DETAILS BY USER ID][{}][{}]", userId, requestId);
        responseInfo = borrowingDetailUsecase.getAllBorrowingDetailsByUserId(userId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get all borrowing with status "dipinjam" by user id and book title
    @GetMapping("/all/userId/bookTitle")
    ResponseEntity<Object> getAllBorrowingDetailsByUserIdAndBookTitle(@RequestHeader(value = "request-id", required = false) String requestId,
                                                                      @RequestParam(value = "userId") String userId,
                                                                      @RequestParam(value = "bookTitle") String bookTitle) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BorrowingDetail>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BORROWING DETAILS BY USER ID AND BOOK TITLE][{}][{}][{}]", userId, bookTitle, requestId);
        responseInfo = borrowingDetailUsecase.getAllBorrowingDetailsByUserIdAndBookTitle(userId, bookTitle);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/status/name")
    ResponseEntity<Object> getAllBorrowingHistories(@RequestHeader(value = "request-id", required = false) String requestId,
                                                    @RequestParam(value = "status") String status,
                                                    @RequestParam(value = "name") String name) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BorrowingHistories>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BORROWING HISTORIES][{}]", requestId);
        if (name == null){
            responseInfo = borrowingDetailUsecase.getAllBorrowingHistories(status);
        } else {
            responseInfo = borrowingDetailUsecase.getAllBorrowingHistoriesByMemberName(status, name);
        }
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/userId")
    ResponseEntity<Object> getAllBorrowingHistoriesByUserId(@RequestHeader(value = "request-id", required = false) String requestId,
                                                    @RequestParam(value = "userId") String userId,
                                                    @RequestParam(value = "bookTitle") String bookTitle) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BorrowingHistoriesUser>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BORROWING HISTORIES USER][{}][{}][{}]", userId, bookTitle, requestId);
        if (bookTitle == null){
            responseInfo = borrowingDetailUsecase.getAllBorrowingHistoriesByUserId(userId);
        } else {
            responseInfo = borrowingDetailUsecase.getAllBorrowingHistoriesByUserIdAndBookTitle(userId, bookTitle);
        }
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getBorrowingDetailById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "borrowingId") String borrowingId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Borrowing> responseInfo;
        log.info("[REQUEST RECEIVED - GET BORROWING DETAIL BY ID][{}][{}]", borrowingId, requestId);
        responseInfo = borrowingDetailUsecase.getBorrowingDetailById(borrowingId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }


    //get count borrowing status by user id for jumlah koleksi dipinjam in dashboard member
    @GetMapping("/count/userId")
    ResponseEntity<Object> getCountBorrowingAndLateStatusByUserId(@RequestHeader(value = "request-id", required = false) String requestId,
                                                                  @RequestParam(value = "userId") String userId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Integer> responseInfo;
        log.info("[REQUEST RECEIVED - GET BORROWING AND LATE STATUS BY USER ID][{}][{}]", userId, requestId);
        responseInfo = borrowingDetailUsecase.getCountBorrowingStatusByUserId(userId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get count return & lost status by user id for total riwayat peminjaman in dashboard member
    @GetMapping("/histories/userId")
    ResponseEntity<Object> getCountAllBorrowingHistoryByUserId(@RequestHeader(value = "request-id", required = false) String requestId,
                                                                  @RequestParam(value = "userId") String userId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Integer> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BORROWING HISTORY BY USER ID][{}][{}]", userId, requestId);
        responseInfo = borrowingDetailUsecase.getCountAllBorrowingHistoryByUserId(userId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createBorrowingDetail(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid BorrowingDetailRq borrowingDetailRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW BORROWING DETAIL][{}][PAYLOAD: {}]", requestId, borrowingDetailRq);
        ResponseInfo<Borrowing> responseInfo = borrowingDetailUsecase.addNewBorrowingDetail(borrowingDetailRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateBorrowingDetail(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateBorrowingDetailRq updateBorrowingDetailRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE BORROWING DETAIL][{}][PAYLOAD: {}]", requestId, updateBorrowingDetailRq);
        ResponseInfo<Object> responseInfo = borrowingDetailUsecase.updateBorrowingDetail(updateBorrowingDetailRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteBorrowingDetail(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "borrowingId") String borrowingId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE BORROWING DETAIL][{}][BORROWING ID: {}]", requestId, borrowingId);
        ResponseInfo<Object> responseInfo = borrowingDetailUsecase.deleteBorrowingDetail(borrowingId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
