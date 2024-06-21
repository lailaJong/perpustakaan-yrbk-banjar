package tugasakhir.library.utils.member;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;

import java.util.Date;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class MembersMapperImpl implements MembersMapper {

    @Override
    public Member toMember(MemberRq memberRq) {
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
        return member;
    }

    @Override
    public void updateMemberFromUpdateMemberRq(UpdateMemberRq updateMemberRq, Member member) {
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
    }
}