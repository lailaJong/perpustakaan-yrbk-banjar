package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.entity.BookShelf;
import tugasakhir.library.model.request.bookshelf.BookShelfRq;
import tugasakhir.library.model.request.bookshelf.UpdateBookShelfRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.BookShelfUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book/shelf")
@Slf4j
public class BookShelfController {

    @Autowired
    private BookShelfUsecase bookShelfUsecase;

    @GetMapping("/all")
    ResponseEntity<Object> getAllBookShelves(@RequestHeader(value = "request-id", required = false) String requestId,
                                             @RequestParam(value = "code", required = false) String code) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BookShelf>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BOOK SHELF][{}][{}]", requestId, code);
        if (code == null) {
            responseInfo = bookShelfUsecase.getAllBookShelves();
        } else {
            responseInfo = bookShelfUsecase.getBookShelfByCode(code);
        }
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getBookShelfById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookShelfId") String bookShelfId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<BookShelf> responseInfo;
        log.info("[REQUEST RECEIVED - GET BOOK SHELF BY ID][{}][{}]", bookShelfId, requestId);
        responseInfo = bookShelfUsecase.getBookShelfById(bookShelfId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createBookShelf(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid BookShelfRq bookShelfRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW BOOK SHELF][{}][PAYLOAD: {}]", requestId, bookShelfRq);
        ResponseInfo<BookShelf> responseInfo = bookShelfUsecase.addNewBookShelf(bookShelfRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateBookShelf(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateBookShelfRq updateBookShelfRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE BOOK SHELF][{}][PAYLOAD: {}]", requestId, updateBookShelfRq);
        ResponseInfo<Object> responseInfo = bookShelfUsecase.updateBookShelf(updateBookShelfRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteBookShelf(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookShelfId") String bookShelfId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE BOOK SHELF][{}][BOOK SHELF ID: {}]", requestId, bookShelfId);
        ResponseInfo<Object> responseInfo = bookShelfUsecase.deleteBookShelf(bookShelfId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
