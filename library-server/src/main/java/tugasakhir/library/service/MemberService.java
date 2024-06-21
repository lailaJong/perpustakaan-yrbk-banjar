//package tugasakhir.library.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import tugasakhir.library.model.entity.Member;
//import tugasakhir.library.repository.MemberRepository;
//
//import java.util.List;
//
///**
// * @author Putri Mele
// * on 13/06/2024
// */
//@Service
//public class MemberService {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    public List<Member> getAllMembers() {
//        return memberRepository.getAllMembers();
//    }
//
//    public Member getMemberById(Integer memberId) {
//        return memberRepository.getMemberById(memberId);
//    }
//
//    public Member addMember(Member member) {
//        Member memberResult;
//        memberRepository.addMember(member);
//        memberResult = memberRepository.getMemberById(member.getMemberId());
//        return memberResult;
//    }
//
//    public Member updateMember(Integer memberId, Member memberDetails) {
//        Member member = memberRepository.getMemberById(memberId);
//        if (member != null) {
//            member.setName(memberDetails.getName());
//            member.setGender(memberDetails.getGender());
//            // Set other fields...
//            memberRepository.updateMember(member);
//        }
//        return null;
//    }
//
//    public void deleteMember(Integer memberId) {
//        memberRepository.deleteMember(memberId);
//    }
//}