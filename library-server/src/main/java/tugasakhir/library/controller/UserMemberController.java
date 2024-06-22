package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.dto.UserMember;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.entity.User;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;
import tugasakhir.library.model.request.user.UserRq;
import tugasakhir.library.model.request.usermember.UpdateUserMemberRq;
import tugasakhir.library.model.request.usermember.UserMemberRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.MemberUsecase;
import tugasakhir.library.usecase.UserMemberUsecase;
import tugasakhir.library.usecase.UserUsecase;

import java.util.Date;
import java.util.UUID;

/**
 * @author Putri Mele
 * on 20/06/2024
 */

@RestController
@RequestMapping("api/v1/user/member")
@Slf4j
public class UserMemberController {

    @Autowired
    private UserUsecase userUsecase;

    @Autowired
    private MemberUsecase memberUsecase;

    @Autowired
    private UserMemberUsecase userMemberUsecase;

    //pendaftaran anggota
    @PostMapping("/createUserAndMember")
    ResponseEntity<Object> createUserAndMember(@RequestHeader(value = "request-id", required = false) String requestId,
                                               @RequestBody @Valid UserMemberRq userMemberRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW USER AND MEMBER][{}][PAYLOAD: {}]", requestId, userMemberRq);

        // Create User
        UserRq userRq = new UserRq();
        userRq.setUsername(userMemberRq.getUsername());
        userRq.setPassword(userMemberRq.getPassword());

        ResponseInfo<User> userResponseInfo = userUsecase.addNewUser(userRq);

        if (userResponseInfo.getHttpStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(userResponseInfo.getHttpStatusCode())
                    .headers(userResponseInfo.getHttpHeaders())
                    .body(userResponseInfo.getBody());
        }

        // Create Member
        MemberRq memberRq = new MemberRq();
        memberRq.setName(userMemberRq.getName());
        memberRq.setGender(userMemberRq.getGender());
        memberRq.setPhoneNumber(userMemberRq.getPhoneNumber());
        memberRq.setPlaceOfBirth(userMemberRq.getPlaceOfBirth());
        memberRq.setDateOfBirth(userMemberRq.getDateOfBirth());
        memberRq.setAddress(userMemberRq.getAddress());
        memberRq.setPoint(10);
        memberRq.setRegristrationDate(new Date());

        ResponseInfo<Member> memberResponseInfo = memberUsecase.addNewMember(userRq, memberRq);

        return ResponseEntity.status(memberResponseInfo.getHttpStatusCode())
                .headers(memberResponseInfo.getHttpHeaders())
                .body(memberResponseInfo.getBody());
    }

    //get user member by user id for profile lengkap in member dashboard
    @GetMapping("/id")
    public ResponseEntity<Object> getUserMemberById(@RequestHeader(value = "request-id", required = false) String requestId,
                                                    @RequestParam(value = "userId") String userId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - GET USER MEMBER BY ID][{}][{}]", userId, requestId);
        ResponseInfo<UserMember> responseInfo;
        responseInfo = userMemberUsecase.getUserMemberByUserId(userId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    //edit user member by user id for edit profile in member dashboard
    @PutMapping("/update")
    public ResponseEntity<Object> updateUserMember(@RequestHeader(value = "request-id", required = false) String requestId,
                                                   @RequestBody @Valid UpdateUserMemberRq updateUserMemberRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE USER MEMBER][{}][PAYLOAD: {}]", requestId, updateUserMemberRq);
        ResponseInfo<Object> responseInfo = userMemberUsecase.updateUserMember(updateUserMemberRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }
}
