package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.dto.MemberDetail;
import tugasakhir.library.model.dto.TopBorrowerMember;
import tugasakhir.library.model.dto.UpdateMemberStatusRq;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;
import tugasakhir.library.model.request.user.UserRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.MemberRepository;
import tugasakhir.library.repository.MemberStatusRepository;
import tugasakhir.library.repository.ScoreDetailRepository;
import tugasakhir.library.repository.UserRepository;
import tugasakhir.library.utils.member.MembersMapperImpl;
import tugasakhir.library.utils.scoredetail.ScoreDetailMapperImpl;

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
    private ScoreDetailRepository scoreDetailRepository;

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

    public ResponseInfo<Integer> getCountAllMembers() {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            count = memberRepository.getCountAllMember();
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT ALL MEMBER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT ALL MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<TopBorrowerMember>> getTopBorrowerMembers() {
        ResponseInfo<List<TopBorrowerMember>> responseInfo = new ResponseInfo<>();

        try {
            List<TopBorrowerMember> members;
            members = memberRepository.getTopBorrowerMember();
            members.addAll(memberRepository.getTopBorrowerMember());
            responseInfo.setSuccess(members);
            log.info("[{}][SUCCESS GET TOP BORROWER MEMBER][DATA SIZE: {}]", getClass().getSimpleName(), members.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET TOP BORROWER MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<MemberDetail>> getAllMemberDetails() {
        ResponseInfo<List<MemberDetail>> responseInfo = new ResponseInfo<>();
        try {
            List<Member> members = memberRepository.getAllMembers();
            List<MemberDetail> memberDetails = MembersMapperImpl.toMemberDetailList(members, userRepository, scoreDetailRepository, memberStatusRepository);
            responseInfo.setSuccess(memberDetails);
            log.info("[{}][SUCCESS GET ALL MEMBER DETAIL][DATA SIZE: {}]", getClass().getSimpleName(), members.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL MEMBER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<MemberDetail> getMemberDetailById(String memberId) {
        ResponseInfo<MemberDetail> responseInfo = new ResponseInfo<>();
        try {
            Member member = memberRepository.getMemberById(memberId);
            MemberDetail memberDetail = MembersMapperImpl.toMemberDetail(member, userRepository, scoreDetailRepository, memberStatusRepository);
            responseInfo.setSuccess(memberDetail);
            log.info("[{}][SUCCESS GET MEMBER DETAIL][ID: {}]", getClass().getSimpleName(), memberId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET MEMBER DETAIL][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), memberId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<MemberDetail>> getMembersByStatus(String status) {
        ResponseInfo<List<MemberDetail>> responseInfo = new ResponseInfo<>();
        try {
            String statusId = memberStatusRepository.getMemberStatusByStatus(status).getMemberStatusId();
            List<Member> members = memberRepository.getMembersByStatusId(statusId);
            List<MemberDetail> memberDetails = MembersMapperImpl.toMemberDetailList(members, userRepository, scoreDetailRepository, memberStatusRepository);
            responseInfo.setSuccess(memberDetails);
            log.info("[{}][SUCCESS GET MEMBER DETAIL][STATUS: {}]", getClass().getSimpleName(), status);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET MEMBER DETAIL][STATUS: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), status, ex);
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

    public ResponseInfo<Member> getMemberByUserId(String userId) {
        ResponseInfo<Member> responseInfo = new ResponseInfo<>();

        try {
            Member member;
            member = memberRepository.getMemberByUserId(userId);
            responseInfo.setSuccess(member);
            log.info("[{}][SUCCESS GET MEMBER][ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET MEMBER][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Member> getMemberByName(String name) {
        ResponseInfo<Member> responseInfo = new ResponseInfo<>();

        try {
            Member member;
            member = memberRepository.getMemberByName(name);
            responseInfo.setSuccess(member);
            log.info("[{}][SUCCESS GET MEMBER][NAME: {}]", getClass().getSimpleName(), name);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET MEMBER][NAME: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), name, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Member> addNewMember(MemberRq memberRq) {
        ResponseInfo<Member> responseInfo = new ResponseInfo<>();

        try {
            Member member;
            memberRq.setMemberId(memberRepository.generateMemberId());
            member = MembersMapperImpl.toMember(memberRq);
            memberRepository.addMember(member);
            responseInfo.setSuccess(member);
            log.info("[{}][SUCCESS ADD NEW MEMBER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW MEMBER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Member> addNewMember(UserRq userRq, MemberRq memberRq) {
        ResponseInfo<Member> responseInfo = new ResponseInfo<>();
        try {
            Member member;
            memberRq.setMemberId(memberRepository.generateMemberId());
            memberRq.setUserId(userRepository.getUserByUsername(userRq.getUsername()).getUserId());
            memberRq.setMemberStatusId(memberStatusRepository.getMemberStatusByStatus("ACTIVE").getMemberStatusId());
            memberRq.setScoreDetailId(ScoreDetailMapperImpl.getScoreDetailId(memberRq.getPoint()));
            member = MembersMapperImpl.toMember(memberRq);
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
                MembersMapperImpl.updateMemberFromUpdateMemberRq(updateMemberRq, member);
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

    public ResponseInfo<Object> updateMemberStatus(UpdateMemberStatusRq updateMemberStatusRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();
        try {
            Member member = memberRepository.getMemberById(updateMemberStatusRq.getMemberId());
            if (member != null) {
                member.setMemberStatusId(updateMemberStatusRq.getMemberStatusId());
                memberRepository.updateMember(member);
                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE MEMBER DETAIL]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE MEMBER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
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
