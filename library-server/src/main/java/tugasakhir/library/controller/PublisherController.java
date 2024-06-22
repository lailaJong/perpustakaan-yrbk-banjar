package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.entity.Publisher;
import tugasakhir.library.model.request.publisher.PublisherRq;
import tugasakhir.library.model.request.publisher.UpdatePublisherRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.PublisherUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/publishers")
@Slf4j
public class PublisherController {

    @Autowired
    private PublisherUsecase publisherUsecase;

    @GetMapping("/all")
    ResponseEntity<Object> getAllPublishers(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<Publisher>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL PUBLISHERS][{}]", requestId);
        responseInfo = publisherUsecase.getAllPublishers();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getPublisherById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "publisherId") String publisherId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Publisher> responseInfo;
        log.info("[REQUEST RECEIVED - GET PUBLISHER BY ID][{}][{}]", publisherId, requestId);
        responseInfo = publisherUsecase.getPublisherById(publisherId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/name")
    ResponseEntity<Object> getPublisherByName(@RequestHeader(value = "request-id", required = false) String requestId,
                                            @RequestParam(value = "publisherName") String publisherName) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Publisher> responseInfo;
        log.info("[REQUEST RECEIVED - GET PUBLISHER BY ID][{}][{}]", publisherName, requestId);
        responseInfo = publisherUsecase.getPublisherByName(publisherName);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createPublisher(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid PublisherRq publisherRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW PUBLISHER][{}][PAYLOAD: {}]", requestId, publisherRq);
        ResponseInfo<Publisher> responseInfo = publisherUsecase.addNewPublisher(publisherRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updatePublisher(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdatePublisherRq updatePublisherRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE PUBLISHER][{}][PAYLOAD: {}]", requestId, updatePublisherRq);
        ResponseInfo<Object> responseInfo = publisherUsecase.updatePublisher(updatePublisherRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deletePublisher(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "publisherId") String publisherId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE PUBLISHER][{}][PUBLISHER ID: {}]", requestId, publisherId);
        ResponseInfo<Object> responseInfo = publisherUsecase.deletePublisher(publisherId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
