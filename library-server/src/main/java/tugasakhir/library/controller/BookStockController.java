package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.entity.BookStock;
import tugasakhir.library.model.request.bookstock.BookStockRq;
import tugasakhir.library.model.request.bookstock.UpdateBookStockRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.BookStockUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book/stocks")
@Slf4j
public class BookStockController {

    @Autowired
    private BookStockUsecase bookStockUsecase;

    @GetMapping("/all")
    ResponseEntity<Object> getAllBookStocks(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BookStock>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BOOK STOCKS][{}]", requestId);
        responseInfo = bookStockUsecase.getAllBookStocks();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getBookStockById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookStockId") String bookStockId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<BookStock> responseInfo;
        log.info("[REQUEST RECEIVED - GET BOOK STOCK BY ID][{}][{}]", bookStockId, requestId);
        responseInfo = bookStockUsecase.getBookStockById(bookStockId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createBookStock(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid BookStockRq bookStockRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW BOOK STOCK][{}][PAYLOAD: {}]", requestId, bookStockRq);
        ResponseInfo<BookStock> responseInfo = bookStockUsecase.addNewBookStock(bookStockRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateBookStock(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateBookStockRq updateBookStockRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE BOOK STOCK][{}][PAYLOAD: {}]", requestId, updateBookStockRq);
        ResponseInfo<Object> responseInfo = bookStockUsecase.updateBookStock(updateBookStockRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteBookStock(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookStockId") String bookStockId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE BOOK STOCK][{}][BOOK STOCK ID: {}]", requestId, bookStockId);
        ResponseInfo<Object> responseInfo = bookStockUsecase.deleteBookStock(bookStockId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
