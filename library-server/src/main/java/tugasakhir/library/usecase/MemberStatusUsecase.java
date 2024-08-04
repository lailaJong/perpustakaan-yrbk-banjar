//package tugasakhir.library.usecase;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import tugasakhir.library.model.entity.MemberStatus;
//import tugasakhir.library.model.exception.NotFoundException;
//import tugasakhir.library.model.request.memberstatus.MemberStatusRq;
//import tugasakhir.library.model.request.memberstatus.UpdateMemberStatusRq;
//import tugasakhir.library.model.response.ResponseInfo;
//import tugasakhir.library.repository.MemberStatusRepository;
//import tugasakhir.library.utils.memberstatus.MemberStatusMapper;
//
//import java.util.List;
//
//@Component
//@Slf4j
//public class MemberStatusUsecase {
//    @Autowired
//    private MemberStatusRepository memberStatusRepository;
//
//    public ResponseInfo<List<MemberStatus>> getAllMemberStatus() {
//        ResponseInfo<List<MemberStatus>> responseInfo = new ResponseInfo<>();
//
//        try {
//            List<MemberStatus> members;
//            members = memberStatusRepository.getAllMemberStatuses();
//            members.addAll(memberStatusRepository.getAllMemberStatuses());
//            responseInfo.setSuccess(members);
//            log.info("[{}][SUCCESS GET ALL MEMBER STATUS][DATA SIZE: {}]", getClass().getSimpleName(), members.size());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET ALL MEMBER STATUS][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//    public ResponseInfo<MemberStatus> getMemberStatusById(String memberStatusId) {
//        ResponseInfo<MemberStatus> responseInfo = new ResponseInfo<>();
//
//        try {
//            MemberStatus member;
//            member = memberStatusRepository.getMemberStatusById(memberStatusId);
//            responseInfo.setSuccess(member);
//            log.info("[{}][SUCCESS GET MEMBER STATUS][ID: {}]", getClass().getSimpleName(), memberStatusId);
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET MEMBER STATUS][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), memberStatusId, ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//    public ResponseInfo<String> getStatusByMemberStatusId(String memberStatusId) {
//        ResponseInfo<String> responseInfo = new ResponseInfo<>();
//
//        try {
//            String status = memberStatusRepository.getStatusByMemberStatusId(memberStatusId);
//            responseInfo.setSuccess(status);
//            log.info("[{}][SUCCESS GET MEMBER STATUS][ID: {}]", getClass().getSimpleName(), memberStatusId);
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET MEMBER STATUS][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), memberStatusId, ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//    public ResponseInfo<MemberStatus> addNewMemberStatus(MemberStatusRq memberStatusRq) {
//        ResponseInfo<MemberStatus> responseInfo = new ResponseInfo<>();
//
//        try {
//            MemberStatus memberStatus;
//            memberStatusRq.setMemberStatusId(memberStatusRepository.generateMemberStatusId());
//            memberStatus = MemberStatusMapper.INSTANCE.toMemberStatus(memberStatusRq);
//            memberStatusRepository.addMemberStatus(memberStatus);
//            responseInfo.setSuccess(memberStatus);
//            log.info("[{}][SUCCESS ADD NEW MEMBER STATUS]", getClass().getSimpleName());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED ADD NEW MEMBER STATUS][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//    public ResponseInfo<Object> updateMemberStatus(UpdateMemberStatusRq updateMemberStatusRq) {
//        ResponseInfo<Object> responseInfo = new ResponseInfo<>();
//
//        try {
//            MemberStatus memberStatus = memberStatusRepository.getMemberStatusById(updateMemberStatusRq.getMemberStatusId());
//            if (memberStatus != null) {
//                MemberStatusMapper.INSTANCE.updateMemberStatusFromUpdateMemberStatusRq(updateMemberStatusRq, memberStatus);
//                memberStatusRepository.updateMemberStatus(memberStatus);
//                responseInfo.setSuccess();
//            } else {
//                throw new NotFoundException();
//            }
//            log.info("[{}][SUCCESS UPDATE MEMBER STATUS]", getClass().getSimpleName());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED UPDATE MEMBER STATUS][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//
//    public ResponseInfo<Object> deleteMemberStatus(String memberStatusId) {
//        ResponseInfo<Object> responseInfo = new ResponseInfo<>();
//
//        try {
//            memberStatusRepository.deleteMemberStatus(memberStatusId);
//            responseInfo.setSuccess();
//            log.info("[{}][SUCCESS DELETE MEMBER STATUS][{}]", getClass().getSimpleName(), memberStatusId);
//        } catch (Exception ex) {
//            log.info("[{}][FAILED DELETE MEMBER STATUS][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
//
//}
