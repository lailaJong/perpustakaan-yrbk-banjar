package tugasakhir.library.usecase;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.entity.User;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;
import tugasakhir.library.model.request.user.UserRq;
import tugasakhir.library.model.request.usermember.UserMemberRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.MemberRepository;
import tugasakhir.library.repository.MemberStatusRepository;
import tugasakhir.library.repository.UserRepository;
import tugasakhir.library.utils.member.MembersMapper;
import tugasakhir.library.utils.scoredetail.ScoreDetailMapper;

import java.util.List;

@Component
@Slf4j
public class MemberUsecase {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MemberStatusRepository memberStatusRepository;
    @Autowired
    private UserUsecase userUsecase;

    public ResponseInfo<List<Member>> getAllMembers() {
        ResponseInfo<List<Member>> responseInfo = new ResponseInfo<>();

        try {
            List<Member> members;
            members = memberRepository.getAllMembers();
            members.addAll(memberRepository.getAllMembers());
            responseInfo.setSuccess(members);
            log.info("[{}][SUCCESS GET ALL MEMBER][DATA SIZE: {}]", getClass().getSimpleName(), members.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Member> getMemberById(String memberId) {
        ResponseInfo<Member> responseInfo = new ResponseInfo<>();

        try {
            Member member;
            member = memberRepository.getMemberById(memberId);
            responseInfo.setSuccess(member);
            log.info("[{}][SUCCESS GET MEMBER][ID: {}]", getClass().getSimpleName(), memberId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET MEMBER][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), memberId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Member> addNewMember(MemberRq memberRq) {
        ResponseInfo<Member> responseInfo = new ResponseInfo<>();

        try {
            Member member;
            memberRq.setMemberId(memberRepository.generateMemberId());
            member = MembersMapper.INSTANCE.toMember(memberRq);
            memberRepository.addMember(member);
            responseInfo.setSuccess(member);
            log.info("[{}][SUCCESS ADD NEW MEMBER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Member> addNewUserMember(UserRq userRq, MemberRq memberRq) {
        ResponseInfo<Member> responseInfo = new ResponseInfo<>();
        try {
            Member member;
            memberRq.setMemberId(memberRepository.generateMemberId());
            memberRq.setUserId(userRepository.getUserByUsername(userRq.getUsername()).getUserId());
            memberRq.setMemberStatusId(memberStatusRepository.getMemberStatusByStatus("ACTIVE").getMemberStatusId());
            memberRq.setScoreDetailId(ScoreDetailMapper.INSTANCE.getScoreDetailId(10));
            member = MembersMapper.INSTANCE.toMember(memberRq);
            memberRepository.addMember(member);
            responseInfo.setSuccess(member);
            log.info("[{}][SUCCESS ADD NEW MEMBER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateMember(UpdateMemberRq updateMemberRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Member member = memberRepository.getMemberById(updateMemberRq.getMemberId());
            if (member != null) {
                MembersMapper.INSTANCE.updateMemberFromUpdateMemberRq(updateMemberRq, member);
                memberRepository.updateMember(member);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE MEMBER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteMember(String memberId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            memberRepository.deleteMember(memberId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE MEMBER][{}]", getClass().getSimpleName(), memberId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
