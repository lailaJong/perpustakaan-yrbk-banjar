//package tugasakhir.library.controller;
//
//import jakarta.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tugasakhir.library.model.entity.ScoreDetail;
//import tugasakhir.library.model.request.scoredetail.ScoreDetailRq;
//import tugasakhir.library.model.request.scoredetail.UpdateScoreDetailRq;
//import tugasakhir.library.model.response.ResponseInfo;
//import tugasakhir.library.usecase.ScoreDetailUsecase;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/score")
//@Slf4j
//public class ScoreDetailController {
//
//    @Autowired
//    private ScoreDetailUsecase scoreDetailUsecase;
//
//    @GetMapping("/all")
//    ResponseEntity<Object> getAllScoreDetails(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<ScoreDetail>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ALL SCORE DETAILS][{}]", requestId);
//        responseInfo = scoreDetailUsecase.getAllScoreDetails();
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    //getScoreDetailById for info benefit in dashboard member
//    @GetMapping("/id")
//    ResponseEntity<Object> getScoreDetailById(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "scoreDetailId") String scoreDetailId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<ScoreDetail> responseInfo;
//        log.info("[REQUEST RECEIVED - GET SCORE DETAIL BY ID][{}][{}]", scoreDetailId, requestId);
//        responseInfo = scoreDetailUsecase.getScoreDetailById(scoreDetailId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PostMapping("/create")
//    ResponseEntity<Object> createScoreDetail(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid ScoreDetailRq scoreDetailRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - ADD NEW SCORE DETAIL][{}][PAYLOAD: {}]", requestId, scoreDetailRq);
//        ResponseInfo<ScoreDetail> responseInfo = scoreDetailUsecase.addNewScoreDetail(scoreDetailRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PutMapping("/update")
//    ResponseEntity<Object> updateScoreDetail(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid UpdateScoreDetailRq updateScoreDetailRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - UPDATE SCORE DETAIL][{}][PAYLOAD: {}]", requestId, updateScoreDetailRq);
//        ResponseInfo<Object> responseInfo = scoreDetailUsecase.updateScoreDetail(updateScoreDetailRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @DeleteMapping("/delete")
//    ResponseEntity<Object> deleteScoreDetail(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "scoreDetailId") String scoreDetailId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - DELETE SCORE DETAIL][{}][SCORE DETAIL ID: {}]", requestId, scoreDetailId);
//        ResponseInfo<Object> responseInfo = scoreDetailUsecase.deleteScoreDetail(scoreDetailId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//}
