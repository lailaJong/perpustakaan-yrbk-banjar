package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.AuthorUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/authors")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorUsecase authorUsecase;

    @GetMapping("/all")
    ResponseEntity<Object> getAllAuthors(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<Author>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL AUTHORS][{}]", requestId);
        responseInfo = authorUsecase.getAllAuthors();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getAuthorById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "authorId") String authorId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Author> responseInfo;
        log.info("[REQUEST RECEIVED - GET AUTHOR BY ID][{}][{}]", authorId, requestId);
        responseInfo = authorUsecase.getAuthorById(authorId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createAuthor(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid AuthorRq authorRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW AUTHOR][{}][PAYLOAD : {}]", requestId, authorRq);
        ResponseInfo<Author> responseInfo = authorUsecase.addNewAuthor(authorRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateAuthor(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateAuthorRq updateAuthorRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE AUTHOR][{}][PAYLOAD : {}]", requestId, updateAuthorRq);
        ResponseInfo<Object> responseInfo = authorUsecase.updateAuthor(updateAuthorRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteAuthor(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "authorId") String authorId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE AUTHOR][{}][AUTHOR ID : {}]", requestId, authorId);
        ResponseInfo<Object> responseInfo = authorUsecase.deleteAuthor(authorId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }
}
