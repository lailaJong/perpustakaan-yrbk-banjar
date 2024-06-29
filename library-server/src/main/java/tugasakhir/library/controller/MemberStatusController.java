//package tugasakhir.library.controller;
//
//import jakarta.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tugasakhir.library.model.entity.MemberStatus;
//import tugasakhir.library.model.request.memberstatus.MemberStatusRq;
//import tugasakhir.library.model.request.memberstatus.UpdateMemberStatusRq;
//import tugasakhir.library.model.response.ResponseInfo;
//import tugasakhir.library.usecase.MemberStatusUsecase;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/member/status")
//@Slf4j
//public class MemberStatusController {
//
//    @Autowired
//    private MemberStatusUsecase memberStatusUsecase;
//
//    @GetMapping("/all")
//    ResponseEntity<Object> getAllMemberStatus(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<MemberStatus>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ALL MEMBER STATUS][{}]", requestId);
//        responseInfo = memberStatusUsecase.getAllMemberStatus();
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @GetMapping("/id")
//    ResponseEntity<Object> getMemberStatusById(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "memberStatusId") String memberStatusId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<MemberStatus> responseInfo;
//        log.info("[REQUEST RECEIVED - GET MEMBER STATUS BY ID][{}][{}]", memberStatusId, requestId);
//        responseInfo = memberStatusUsecase.getMemberStatusById(memberStatusId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    //get member status by member status id for status anggota in dashboard member
//    @GetMapping("/status/id")
//    ResponseEntity<Object> getStatusByMemberStatusId(@RequestHeader(value = "request-id", required = false) String requestId,
//                                               @RequestParam(value = "memberStatusId") String memberStatusId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<String> responseInfo;
//        log.info("[REQUEST RECEIVED - GET MEMBER STATUS BY ID][{}][{}]", memberStatusId, requestId);
//        responseInfo = memberStatusUsecase.getStatusByMemberStatusId(memberStatusId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PostMapping("/create")
//    ResponseEntity<Object> createMemberStatus(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid MemberStatusRq memberStatusRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - ADD NEW MEMBER STATUS][{}][PAYLOAD: {}]", requestId, memberStatusRq);
//        ResponseInfo<MemberStatus> responseInfo = memberStatusUsecase.addNewMemberStatus(memberStatusRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PutMapping("/update")
//    ResponseEntity<Object> updateMemberStatus(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid UpdateMemberStatusRq updateMemberStatusRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - UPDATE MEMBER STATUS][{}][PAYLOAD: {}]", requestId, updateMemberStatusRq);
//        ResponseInfo<Object> responseInfo = memberStatusUsecase.updateMemberStatus(updateMemberStatusRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @DeleteMapping("/delete")
//    ResponseEntity<Object> deleteMemberStatus(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "memberStatusId") String memberStatusId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - DELETE MEMBER STATUS][{}][MEMBER STATUS ID: {}]", requestId, memberStatusId);
//        ResponseInfo<Object> responseInfo = memberStatusUsecase.deleteMemberStatus(memberStatusId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//}
