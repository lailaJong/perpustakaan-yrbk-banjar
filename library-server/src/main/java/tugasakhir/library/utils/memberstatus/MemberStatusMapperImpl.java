package tugasakhir.library.utils.memberstatus;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.entity.MemberStatus;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.memberstatus.MemberStatusRq;
import tugasakhir.library.model.request.memberstatus.UpdateMemberStatusRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class MemberStatusMapperImpl implements MemberStatusMapper {

    @Override
    public MemberStatus toMemberStatus(MemberStatusRq memberStatusRq) {
        if (memberStatusRq == null) {
            return null;
        }
        MemberStatus memberStatus = new MemberStatus();
        memberStatus.setMemberStatusId(memberStatusRq.getMemberStatusId());
        memberStatus.setStatus(memberStatusRq.getStatus());
        return memberStatus;
    }

    @Override
    public void updateMemberStatusFromUpdateMemberStatusRq(UpdateMemberStatusRq updateMemberStatusRq, MemberStatus memberStatus) {
        if ( updateMemberStatusRq == null ) {
            return;
        }

        if ( updateMemberStatusRq.getStatus() != null ) {
            memberStatus.setStatus( updateMemberStatusRq.getStatus() );
        }
    }
}