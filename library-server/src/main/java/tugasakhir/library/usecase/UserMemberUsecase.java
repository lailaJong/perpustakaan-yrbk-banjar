package tugasakhir.library.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import tugasakhir.library.model.dto.UserMember;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.entity.User;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.usermember.UpdateUserMemberRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.MemberRepository;
import tugasakhir.library.repository.UserRepository;
import tugasakhir.library.utils.member.MembersMapperImpl;
import tugasakhir.library.utils.user.UserMapperImpl;

import java.text.SimpleDateFormat;

@Component
@Slf4j
public class UserMemberUsecase {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseInfo<UserMember> getUserMemberByUserId(String userId) {
        ResponseInfo<UserMember> responseInfo = new ResponseInfo<>();

        try {
            User user;
            Member member;
            user = userRepository.getUserById(userId);
            member = memberRepository.getMemberByUserId(userId);
            UserMember userMember = new UserMember()
                    .setName(member.getName())
                    .setUsername(user.getUsername())
                    .setGender(member.getGender())
                    .setPhoneNumber(member.getPhoneNumber())
                    .setPlaceOfBirth(member.getPlaceOfBirth())
                    .setDateOfBirth(member.getDateOfBirth())
                    .setAddress(member.getAddress())
                    .setRegistrationDate(member.getRegistrationDate());
            responseInfo.setSuccess(userMember);
            log.info("[{}][SUCCESS GET USER MEMBER][USER ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET USER MEMBER][USER ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateUserMember(UpdateUserMemberRq updateUserMemberRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            boolean isExist = userRepository.existsByUserId(updateUserMemberRq.getUserId());
            if (!isExist) {
                responseInfo.setBussinessError(updateUserMemberRq.getUserId() + " is not exist");
                log.info("[{}][FAILED UPDATE PUBLISHER]", getClass().getSimpleName());
            } else {
                User user = userRepository.getUserById(updateUserMemberRq.getUserId());
                Member member = memberRepository.getMemberByUserId(updateUserMemberRq.getUserId());
                if (member != null) {
                    UserMapperImpl.updateUserFromUpdateUserRq(updateUserMemberRq, user);
                    MembersMapperImpl.updateMemberFromUpdateMemberRq(updateUserMemberRq, member);
                    userRepository.updateUser(user);
                    memberRepository.updateMember(member);
                    responseInfo.setSuccess();
                    log.info("[{}][SUCCESS UPDATE USER AND MEMBER]", getClass().getSimpleName());
                } else {
                    responseInfo.setBussinessError(member.getMemberId() + " is not exist");
                    log.info("[{}][FAILED UPDATE PUBLISHER]", getClass().getSimpleName());
                }
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE USER AND MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

}
