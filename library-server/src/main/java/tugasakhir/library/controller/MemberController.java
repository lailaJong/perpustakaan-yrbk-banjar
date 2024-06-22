package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;
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

    @GetMapping("/all")
    ResponseEntity<Object> getAllMembers(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<Member>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL MEMBERS][{}]", requestId);
        responseInfo = memberUsecase.getAllMembers();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getMemberById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "memberId") String memberId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Member> responseInfo;
        log.info("[REQUEST RECEIVED - GET MEMBER BY ID][{}][{}]", memberId, requestId);
        responseInfo = memberUsecase.getMemberById(memberId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //get member by user id after sign up/login
    @GetMapping("/user-id")
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

    @PostMapping("/create")
    ResponseEntity<Object> createMember(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid MemberRq memberRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW MEMBER][{}][PAYLOAD: {}]", requestId, memberRq);
        ResponseInfo<Member> responseInfo = memberUsecase.addNewMember(memberRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateMember(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateMemberRq updateMemberRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE MEMBER][{}][PAYLOAD: {}]", requestId, updateMemberRq);
        ResponseInfo<Object> responseInfo = memberUsecase.updateMember(updateMemberRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteMember(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "memberId") String memberId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE MEMBER][{}][MEMBER ID: {}]", requestId, memberId);
        ResponseInfo<Object> responseInfo = memberUsecase.deleteMember(memberId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
