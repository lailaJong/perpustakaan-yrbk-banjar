//package tugasakhir.library.controller;
//
//import jakarta.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tugasakhir.library.model.entity.Officer;
//import tugasakhir.library.model.request.officer.OfficerRq;
//import tugasakhir.library.model.request.officer.UpdateOfficerRq;
//import tugasakhir.library.model.response.ResponseInfo;
//import tugasakhir.library.usecase.OfficerUsecase;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/officers")
//@Slf4j
//public class OfficerController {
//
//    @Autowired
//    private OfficerUsecase officerUsecase;
//
//    @GetMapping("/all")
//    ResponseEntity<Object> getAllOfficers(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<Officer>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ALL OFFICERS][{}]", requestId);
//        responseInfo = officerUsecase.getAllOfficers();
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @GetMapping("/id")
//    ResponseEntity<Object> getOfficerById(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "officerId") String officerId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<Officer> responseInfo;
//        log.info("[REQUEST RECEIVED - GET OFFICER BY ID][{}][{}]", officerId, requestId);
//        responseInfo = officerUsecase.getOfficerById(officerId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PostMapping("/create")
//    ResponseEntity<Object> createOfficer(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid OfficerRq officerRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - ADD NEW OFFICER][{}][PAYLOAD: {}]", requestId, officerRq);
//        ResponseInfo<Officer> responseInfo = officerUsecase.addNewOfficer(officerRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PutMapping("/update")
//    ResponseEntity<Object> updateOfficer(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid UpdateOfficerRq updateOfficerRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - UPDATE OFFICER][{}][PAYLOAD: {}]", requestId, updateOfficerRq);
//        ResponseInfo<Object> responseInfo = officerUsecase.updateOfficer(updateOfficerRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @DeleteMapping("/delete")
//    ResponseEntity<Object> deleteOfficer(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "officerId") String officerId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - DELETE OFFICER][{}][OFFICER ID: {}]", requestId, officerId);
//        ResponseInfo<Object> responseInfo = officerUsecase.deleteOfficer(officerId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//}
