package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.dto.ListMember;
import tugasakhir.library.model.dto.MemberDetail;
import tugasakhir.library.model.dto.TopBorrowerMember;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;
import tugasakhir.library.model.request.memberstatus.UpdateMemberStatusRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.MemberUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/members")
@Slf4j
public class MemberController {

    @Autowired
    private MemberUsecase memberUsecase;

    @GetMapping("/officer/all/list")
    ResponseEntity<Object> getAllMemberNames(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<ListMember>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL MEMBER NAMES][{}]", requestId);
        responseInfo = memberUsecase.getAllMemberNames();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/all/count")
    ResponseEntity<Object> getCountAllMembers(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Integer> responseInfo;
        log.info("[REQUEST RECEIVED - GET COUNT ALL MEMBERS][{}]", requestId);
        responseInfo = memberUsecase.getCountAllMembers();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/officer/top")
    ResponseEntity<Object> getTopBorrowerMembers(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<TopBorrowerMember>> responseInfo;
        log.info("[REQUEST RECEIVED - GET TOP BORROWER MEMBERS][{}]", requestId);
        responseInfo = memberUsecase.getTopBorrowerMembers();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/all/detail")
    ResponseEntity<Object> getAllMemberDetails(@RequestHeader(value = "request-id", required = false) String requestId,
                                               @RequestParam(value = "memberName", required = false) String memberName) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<MemberDetail>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL MEMBER DETAILS][{}]", requestId);
        if (memberName == null){
            responseInfo = memberUsecase.getAllMemberDetails();
        } else {
            responseInfo = memberUsecase.getAllMemberDetailsByName(memberName);
        }
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id/detail")
    ResponseEntity<Object> getMemberDetailById(@RequestHeader(value = "request-id", required = false) String requestId,
                                         @RequestParam(value = "memberId") String memberId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<MemberDetail> responseInfo;
        log.info("[REQUEST RECEIVED - GET MEMBER DETAIL BY ID][{}][{}]", memberId, requestId);
        responseInfo = memberUsecase.getMemberDetailById(memberId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get member by user id after login
    @GetMapping("/userId")
    ResponseEntity<Object> getMemberByUserId(@RequestHeader(value = "request-id", required = false) String requestId,
                                             @RequestParam(value = "userId") String userId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Member> responseInfo;
        log.info("[REQUEST RECEIVED - GET MEMBER BY USER ID][{}][{}]", userId, requestId);
        responseInfo = memberUsecase.getMemberByUserId(userId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update/status")
    ResponseEntity<Object> updateMemberStatus(@RequestHeader(value = "request-id", required = false) String requestId,
                                              @RequestBody @Valid UpdateMemberStatusRq updateMemberStatusRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE MEMBER STATUS][{}][PAYLOAD: {}]", requestId, updateMemberStatusRq);
        ResponseInfo<Object> responseInfo = memberUsecase.updateMemberStatus(updateMemberStatusRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

//    @PutMapping("/update")
//    ResponseEntity<Object> updateMember(@RequestHeader(value = "request-id", required = false) String requestId,
//                                        @RequestBody @Valid UpdateMemberRq updateMemberRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - UPDATE MEMBER][{}][PAYLOAD: {}]", requestId, updateMemberRq);
//        ResponseInfo<Object> responseInfo = memberUsecase.updateMember(updateMemberRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }

//    @DeleteMapping("/delete")
//    ResponseEntity<Object> deleteMember(@RequestHeader(value = "request-id", required = false) String requestId,
//                                        @RequestParam(value = "memberId") String memberId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - DELETE MEMBER][{}][MEMBER ID: {}]", requestId, memberId);
//        ResponseInfo<Object> responseInfo = memberUsecase.deleteMember(memberId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }

//    @GetMapping("/all")
//    ResponseEntity<Object> getAllMembers(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<Member>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ALL MEMBERS][{}]", requestId);
//        responseInfo = memberUsecase.getAllMembers();
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }

//    @GetMapping("/status/active")
//    ResponseEntity<ResponseInfo<List<MemberDetail>>> getMemberDetailsByStatusActive(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<MemberDetail>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ALL MEMBER DETAILS BY ACTIVATE STATUS][{}]", requestId);
//        responseInfo = memberUsecase.getMembersByStatus("ACTIVE");
//        return new ResponseEntity<>(responseInfo, HttpStatus.OK);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }

//    @GetMapping("/status/deactivate")
//    ResponseEntity<ResponseInfo<List<MemberDetail>>> getMemberDetailsByStatusDeactivate(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<MemberDetail>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ALL MEMBER DETAILS BY DEACTIVATE STATUS][{}]", requestId);
//        responseInfo = memberUsecase.getMembersByStatus("DEACTIVATE");
//        return new ResponseEntity<>(responseInfo, HttpStatus.OK);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }

//    @GetMapping("/id")
//    ResponseEntity<Object> getMemberById(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "memberId") String memberId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<Member> responseInfo;
//        log.info("[REQUEST RECEIVED - GET MEMBER BY ID][{}][{}]", memberId, requestId);
//        responseInfo = memberUsecase.getMemberById(memberId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }

//    @GetMapping("/name")
//    ResponseEntity<Object> getMemberByName(@RequestHeader(value = "request-id", required = false) String requestId,
//                                         @RequestParam(value = "memberName") String memberName) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<Member> responseInfo;
//        log.info("[REQUEST RECEIVED - GET MEMBER BY NAME][{}][{}]", memberName, requestId);
//        responseInfo = memberUsecase.getMemberByName(memberName);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }

//    @GetMapping("/name")
//    ResponseEntity<Object> getAllMemberDetailByName(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<MemberDetail>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET MEMBER DETAIL BY NAME][{}][{}]", memberName, requestId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }


//    @PostMapping("/create")
//    ResponseEntity<Object> createMember(@RequestHeader(value = "request-id", required = false) String requestId,
//                                        @RequestBody @Valid MemberRq memberRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - ADD NEW MEMBER][{}][PAYLOAD: {}]", requestId, memberRq);
//        ResponseInfo<Member> responseInfo = memberUsecase.addNewMember(memberRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
}
