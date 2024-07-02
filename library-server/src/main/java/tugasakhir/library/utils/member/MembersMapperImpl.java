package tugasakhir.library.utils.member;

import org.springframework.beans.factory.annotation.Autowired;
import tugasakhir.library.model.dto.MemberDetail;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.entity.MemberStatus;
import tugasakhir.library.model.entity.ScoreDetail;
import tugasakhir.library.model.entity.User;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;
import tugasakhir.library.model.request.usermember.UpdateUserMemberRq;
import tugasakhir.library.repository.MemberStatusRepository;
import tugasakhir.library.repository.ScoreDetailRepository;
import tugasakhir.library.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class MembersMapperImpl {

    public static Member toMember(MemberRq memberRq, String memberId, String userId, String memberStatusId, String scoreDetailId) {
        if (memberRq == null) {
            return null;
        }
        Member member = new Member();
        member.setMemberId(memberId);
        member.setUserId(userId);
        member.setMemberStatusId(memberStatusId);
        member.setScoreDetailId(scoreDetailId);
        member.setName(memberRq.getName());
        member.setGender(memberRq.getGender());
        member.setPhoneNumber(memberRq.getPhoneNumber());
        member.setPlaceOfBirth(memberRq.getPlaceOfBirth());
        member.setDateOfBirth(memberRq.getDateOfBirth());
        member.setAddress(memberRq.getAddress());
        member.setPoint(memberRq.getPoint());
        member.setRegristrationDate(memberRq.getRegristrationDate());
        return member;
    }

    public static void updateMemberFromUpdateMemberRq(UpdateMemberRq updateMemberRq, Member member) {
        if ( updateMemberRq == null ) {
            return;
        }

        if ( updateMemberRq.getUserId() != null ) {
            member.setUserId( updateMemberRq.getUserId() );
        }
        if ( updateMemberRq.getMemberStatusId() != null ) {
            member.setMemberStatusId( updateMemberRq.getMemberStatusId() );
        }
        if ( updateMemberRq.getScoreDetailId() != null ) {
            member.setScoreDetailId( updateMemberRq.getScoreDetailId() );
        }
        if ( updateMemberRq.getName() != null ) {
            member.setName( updateMemberRq.getName() );
        }
        if ( updateMemberRq.getGender() != null ) {
            member.setGender( updateMemberRq.getGender() );
        }
        if ( updateMemberRq.getPhoneNumber() != null ) {
            member.setPhoneNumber( updateMemberRq.getPhoneNumber() );
        }
        if ( updateMemberRq.getPlaceOfBirth() != null ) {
            member.setPlaceOfBirth( updateMemberRq.getPlaceOfBirth() );
        }
        if ( updateMemberRq.getDateOfBirth() != null ) {
            member.setDateOfBirth( updateMemberRq.getDateOfBirth() );
        }
        if ( updateMemberRq.getAddress() != null ) {
            member.setAddress( updateMemberRq.getAddress() );
        }
        if (updateMemberRq.getPoint() != -50){
            member.setPoint( updateMemberRq.getPoint() );
        }
        if ( updateMemberRq.getRegristrationDate() != null ) {
            member.setRegristrationDate( updateMemberRq.getRegristrationDate() );
        }
    }
    public static void updateMemberFromUpdateMemberRq(UpdateUserMemberRq updateUserMemberRq, Member member) {
        if ( updateUserMemberRq == null ) {
            return;
        }

        if ( updateUserMemberRq.getUserId() != null ) {
            member.setUserId( updateUserMemberRq.getUserId() );
        }
        if ( updateUserMemberRq.getName() != null ) {
            member.setName( updateUserMemberRq.getName() );
        }
        if ( updateUserMemberRq.getGender() != null ) {
            member.setGender( updateUserMemberRq.getGender() );
        }
        if ( updateUserMemberRq.getPhoneNumber() != null ) {
            member.setPhoneNumber( updateUserMemberRq.getPhoneNumber() );
        }
        if ( updateUserMemberRq.getPlaceOfBirth() != null ) {
            member.setPlaceOfBirth( updateUserMemberRq.getPlaceOfBirth() );
        }
        if ( updateUserMemberRq.getDateOfBirth() != null ) {
            member.setDateOfBirth( updateUserMemberRq.getDateOfBirth() );
        }
        if ( updateUserMemberRq.getAddress() != null ) {
            member.setAddress( updateUserMemberRq.getAddress() );
        }
    }

    public static MemberDetail toMemberDetail(Member member, UserRepository userRepository, ScoreDetailRepository scoreDetailRepository, MemberStatusRepository memberStatusRepository) {
        if (member == null) {
            return null;
        }
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.setName(member.getName());
        User user = userRepository.getUserById(member.getUserId());
        memberDetail.setUsername(user.getUsername());
        memberDetail.setGender(member.getGender());
        memberDetail.setPlaceOfBirth(member.getPlaceOfBirth());
        memberDetail.setDateOfBirth(member.getDateOfBirth());
        memberDetail.setAddress(member.getAddress());
        memberDetail.setPhoneNumber(member.getPhoneNumber());
        memberDetail.setPoint(member.getPoint());
        ScoreDetail scoreDetail = scoreDetailRepository.getScoreDetailById(member.getScoreDetailId());
        memberDetail.setExtraBorrowTime(scoreDetail.getExtraBorrowTime());
        memberDetail.setExtraBooksQuota(scoreDetail.getExtraBooksQuota());
        MemberStatus status = memberStatusRepository.getMemberStatusById(member.getMemberStatusId());
        memberDetail.setStatus(status.getStatus());
        return memberDetail;
    }

    public static List<MemberDetail> toMemberDetailList(List<Member> members, UserRepository userRepository, ScoreDetailRepository scoreDetailRepository, MemberStatusRepository memberStatusRepository) {
        List<MemberDetail> memberDetails = new ArrayList<>();
        for (Member member : members) {
            memberDetails.add(toMemberDetail(member, userRepository, scoreDetailRepository, memberStatusRepository));
        }
        return memberDetails;
    }
}