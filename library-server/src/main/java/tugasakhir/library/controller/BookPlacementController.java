package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.entity.BookPlacement;
import tugasakhir.library.model.request.bookplacement.BookPlacementRq;
import tugasakhir.library.model.request.bookplacement.UpdateBookPlacementRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.BookPlacementUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book/placements")
@Slf4j
public class BookPlacementController {

    @Autowired
    private BookPlacementUsecase bookPlacementUsecase;

    @GetMapping("/all")
    ResponseEntity<Object> getAllBookPlacements(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BookPlacement>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BOOK PLACEMENTS][{}]", requestId);
        responseInfo = bookPlacementUsecase.getAllBookPlacements();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getBookPlacementById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookPlacementId") String bookPlacementId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<BookPlacement> responseInfo;
        log.info("[REQUEST RECEIVED - GET BOOK PLACEMENT BY ID][{}][{}]", bookPlacementId, requestId);
        responseInfo = bookPlacementUsecase.getBookPlacementById(bookPlacementId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createBookPlacement(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid BookPlacementRq bookPlacementRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW BOOK PLACEMENT][{}][PAYLOAD: {}]", requestId, bookPlacementRq);
        ResponseInfo<BookPlacement> responseInfo = bookPlacementUsecase.addNewBookPlacement(bookPlacementRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateBookPlacement(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateBookPlacementRq updateBookPlacementRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE BOOK PLACEMENT][{}][PAYLOAD: {}]", requestId, updateBookPlacementRq);
        ResponseInfo<Object> responseInfo = bookPlacementUsecase.updateBookPlacement(updateBookPlacementRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteBookPlacement(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookPlacementId") String bookPlacementId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE BOOK PLACEMENT][{}][BOOK PLACEMENT ID: {}]", requestId, bookPlacementId);
        ResponseInfo<Object> responseInfo = bookPlacementUsecase.deleteBookPlacement(bookPlacementId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
