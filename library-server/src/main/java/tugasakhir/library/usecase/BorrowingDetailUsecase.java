package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.config.properties.ApplicationProperties;
import tugasakhir.library.model.entity.BorrowingDetail;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.borrowingdetail.BorrowingDetailRq;
import tugasakhir.library.model.request.borrowingdetail.UpdateBorrowingDetailRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.BorrowingDetailRepository;
import tugasakhir.library.utils.borrowingdetail.BorrowingDetailMapper;

import java.util.List;

@Component
@Slf4j
public class BorrowingDetailUsecase {
    @Autowired
    private BorrowingDetailRepository borrowingDetailRepository;
    @Autowired
    private ApplicationProperties applicationProperties;

    public ResponseInfo<List<BorrowingDetail>> getAllBorrowingDetails() {
        ResponseInfo<List<BorrowingDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingDetail> borrowingDetails;
            borrowingDetails = borrowingDetailRepository.getAllBorrowingDetails();
            borrowingDetails.addAll(borrowingDetailRepository.getAllBorrowingDetails());
            responseInfo.setSuccess(borrowingDetails);
            log.info("[{}][SUCCESS GET ALL BORROWING DETAIL][DATA SIZE: {}]", getClass().getSimpleName(), borrowingDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BorrowingDetail> getBorrowingDetailById(String borrowingId) {
        ResponseInfo<BorrowingDetail> responseInfo = new ResponseInfo<>();

        try {
            BorrowingDetail borrowingDetail;
            borrowingDetail = borrowingDetailRepository.getBorrowingDetailById(borrowingId);
            responseInfo.setSuccess(borrowingDetail);
            log.info("[{}][SUCCESS GET BORROWING DETAIL][ID: {}]", getClass().getSimpleName(), borrowingId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BORROWING DETAIL][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), borrowingId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Integer> getCountBorrowingAndLateStatusByUserId(String userId) {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            String borrowingStatus = applicationProperties.getBorrowedStatus();
            String lateStatus = applicationProperties.getLateStatus();
            count = borrowingDetailRepository.getCountBorrowingAndLateStatusByUserId(userId, borrowingStatus, lateStatus);
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT BORROWING AND LATE STATUS][USER ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT BORROWING AND LATE STATUS][USER ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Integer> getCountReturnStatusByUserId(String userId) {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            String returnStatus = applicationProperties.getReturnedStatus();
            count = borrowingDetailRepository.getCountReturnStatusByUserId(userId, returnStatus);
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT RETURN STATUS][USER ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT RETURN STATUS][USER ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BorrowingDetail> addNewBorrowingDetail(BorrowingDetailRq borrowingDetailRq) {
        ResponseInfo<BorrowingDetail> responseInfo = new ResponseInfo<>();

        try {
            BorrowingDetail borrowingDetail;
            borrowingDetailRq.setBorrowingId(borrowingDetailRepository.generateBorrowingDetailId());
            borrowingDetail = BorrowingDetailMapper.INSTANCE.toBorrowingDetail(borrowingDetailRq);
            borrowingDetailRepository.addBorrowingDetail(borrowingDetail);
            responseInfo.setSuccess(borrowingDetail);
            log.info("[{}][SUCCESS ADD NEW BORROWING DETAIL]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BORROWING DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBorrowingDetail(UpdateBorrowingDetailRq updateBorrowingDetailRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            BorrowingDetail borrowingDetail = borrowingDetailRepository.getBorrowingDetailById(updateBorrowingDetailRq.getBorrowingId());
            if (borrowingDetail != null) {
                BorrowingDetailMapper.INSTANCE.updateBorrowingDetailFromUpdateBorrowingDetailRq(updateBorrowingDetailRq, borrowingDetail);
                borrowingDetailRepository.updateBorrowingDetail(borrowingDetail);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE BORROWING DETAIL]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BORROWING DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteBorrowingDetail(String borrowingId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            borrowingDetailRepository.deleteBorrowingDetail(borrowingId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE BORROWING DETAIL][{}]", getClass().getSimpleName(), borrowingId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE BORROWING DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
