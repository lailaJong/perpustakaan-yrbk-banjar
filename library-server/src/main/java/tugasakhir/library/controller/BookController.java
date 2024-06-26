package tugasakhir.library.controller;

import tugasakhir.library.model.dto.BookDetail;
import tugasakhir.library.model.dto.ListBook;
import tugasakhir.library.model.dto.TopBorrowedBook;
import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.request.book.BookRq;
import tugasakhir.library.model.request.book.UpdateBookRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.BookUsecase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
@Slf4j
public class BookController {

    @Autowired
    private BookUsecase bookUsecase;

    //get all books
    @GetMapping("/all")
    ResponseEntity<Object> getAllBooks(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<Book>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BOOKS][{}]", requestId);
        responseInfo = bookUsecase.getAllBooks();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get all book titles
    @GetMapping("/allTitle")
    ResponseEntity<Object> getAllBookTitle(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<ListBook>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BOOK NAMES][{}]", requestId);
        responseInfo = bookUsecase.getAllBookTitles();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }


    //get all books detail
    @GetMapping("/all/details")
    ResponseEntity<Object> getAllBooksDetails(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BookDetail>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BOOKS][{}]", requestId);
        responseInfo = bookUsecase.getAllBooksDetail();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/countAll")
    ResponseEntity<Object> getCountAllBooks(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Integer> responseInfo;
        log.info("[REQUEST RECEIVED - GET COUNT ALL BOOKS][{}]", requestId);
        responseInfo = bookUsecase.getCountAllBooks();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get top borrowed books
    @GetMapping("/top")
    ResponseEntity<Object> getTopBorrowedBooks(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<TopBorrowedBook>> responseInfo;
        log.info("[REQUEST RECEIVED - GET TOP BORROWED BOOKS][{}]", requestId);
        responseInfo = bookUsecase.getTopBorrowedBooks();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get book by book id
    @GetMapping("/id")
    ResponseEntity<Object> getBookById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookId") String bookId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<BookDetail> responseInfo;
        log.info("[REQUEST RECEIVED - GET BOOK DETAILS BY ID][{}][{}]", bookId, requestId);
        responseInfo = bookUsecase.getBookById(bookId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get all books by book title
    @GetMapping("/title")
    ResponseEntity<Object> getAllBooksByTitle(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookTitle") String bookTitle) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<BookDetail>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL BOOKS BY TITLE][{}][{}]", bookTitle, requestId);
        responseInfo = bookUsecase. getBookByTitle(bookTitle);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createBooks(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid BookRq bookRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW BOOK][{}][PAYLOAD: {}]", requestId, bookRq);
        ResponseInfo<Book> responseInfo = bookUsecase.addNewBook(bookRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateBooks(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateBookRq updateBookRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE BOOK][{}][PAYLOAD: {}]", requestId, updateBookRq);
        ResponseInfo<Object> responseInfo = bookUsecase.updateBook(updateBookRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteBooks(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "bookId") String bookId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE BOOK][{}][BOOK ID: {}]", requestId, bookId);
        ResponseInfo<Object> responseInfo = bookUsecase.deleteBook(bookId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
