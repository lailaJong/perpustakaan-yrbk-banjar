package tugasakhir.library.utils.member;

import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;
import tugasakhir.library.model.request.usermember.UpdateUserMemberRq;

import java.util.Date;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class MembersMapperImpl {

    public static Member toMember(MemberRq memberRq) {
        if (memberRq == null) {
            return null;
        }
        Member member = new Member();
        member.setMemberId(memberRq.getMemberId());
        member.setUserId(memberRq.getUserId());
        member.setMemberStatusId(memberRq.getMemberStatusId());
        member.setScoreDetailId(memberRq.getScoreDetailId());
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
}