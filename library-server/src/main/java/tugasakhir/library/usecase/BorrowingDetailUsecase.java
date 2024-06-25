package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.config.properties.ApplicationProperties;
import tugasakhir.library.model.dto.BorrowingDetail;
import tugasakhir.library.model.dto.BorrowingHistories;
import tugasakhir.library.model.dto.BorrowingHistoriesUser;
import tugasakhir.library.model.entity.Borrowing;
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

    public ResponseInfo<List<Borrowing>> getAllBorrowingDetails() {
        ResponseInfo<List<Borrowing>> responseInfo = new ResponseInfo<>();

        try {
            List<Borrowing> borrowingDetails;
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

    public ResponseInfo<List<BorrowingDetail>> getAllBorrowingDetailsByUserId(String userId) {
        ResponseInfo<List<BorrowingDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingDetail> borrowingDetails;
            borrowingDetails = borrowingDetailRepository.getAllBorrowingDetailsByUserId(userId);
            borrowingDetails.addAll(borrowingDetailRepository.getAllBorrowingDetailsByUserId(userId));
            responseInfo.setSuccess(borrowingDetails);
            log.info("[{}][SUCCESS GET ALL BORROWING DETAILS BY USER ID][DATA SIZE: {}]", getClass().getSimpleName(), borrowingDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING DETAILS BY USER ID][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<BorrowingDetail>> getAllBorrowingDetailsByUserIdAndBookTitle(String userId, String bookTitle) {
        ResponseInfo<List<BorrowingDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingDetail> borrowingDetails;
            borrowingDetails = borrowingDetailRepository.getAllBorrowingDetailsByUserIdAndBookTitle(userId, bookTitle);
            borrowingDetails.addAll(borrowingDetailRepository.getAllBorrowingDetailsByUserIdAndBookTitle(userId, bookTitle));
            responseInfo.setSuccess(borrowingDetails);
            log.info("[{}][SUCCESS GET ALL BORROWING DETAILS BY USER ID AND BOOK TITLE][DATA SIZE: {}]", getClass().getSimpleName(), borrowingDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING DETAILS BY USER ID AND BOOK TITLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BorrowingHistories>> getAllBorrowingHistories(String status) {
        ResponseInfo<List<BorrowingHistories>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingHistories> borrowingHistories;
            borrowingHistories = borrowingDetailRepository.getAllBorrowingHistories(status);
            borrowingHistories.addAll(borrowingDetailRepository.getAllBorrowingHistories(status));
            responseInfo.setSuccess(borrowingHistories);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES][DATA SIZE: {}]", getClass().getSimpleName(), borrowingHistories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<BorrowingHistories>> getAllBorrowingHistoriesByMemberName(String status, String name) {
        ResponseInfo<List<BorrowingHistories>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingHistories> borrowingHistories;
            borrowingHistories = borrowingDetailRepository.getAllBorrowingHistoriesByMemberName(status, name);
            borrowingHistories.addAll(borrowingDetailRepository.getAllBorrowingHistoriesByMemberName(status, name));
            responseInfo.setSuccess(borrowingHistories);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES BY NAME][DATA SIZE: {}]", getClass().getSimpleName(), borrowingHistories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES BY NAME][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BorrowingHistoriesUser>> getAllBorrowingHistoriesByUserId(String userId) {
        ResponseInfo<List<BorrowingHistoriesUser>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingHistoriesUser> borrowingHistories;
            borrowingHistories = borrowingDetailRepository.getAllBorrowingHistoriesByUserId(userId);
            borrowingHistories.addAll(borrowingDetailRepository.getAllBorrowingHistoriesByUserId(userId));
            responseInfo.setSuccess(borrowingHistories);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES BY USER ID][DATA SIZE: {}]", getClass().getSimpleName(), borrowingHistories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES BY USER ID][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BorrowingHistoriesUser>> getAllBorrowingHistoriesByUserIdAndBookTitle(String userId, String bookTitle) {
        ResponseInfo<List<BorrowingHistoriesUser>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingHistoriesUser> borrowingHistories;
            borrowingHistories = borrowingDetailRepository.getAllBorrowingHistoriesByUserIdAndBookTitle(userId, bookTitle);
            borrowingHistories.addAll(borrowingDetailRepository.getAllBorrowingHistoriesByUserIdAndBookTitle(userId, bookTitle));
            responseInfo.setSuccess(borrowingHistories);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES BY USER ID AND BOOK TITLE][DATA SIZE: {}]", getClass().getSimpleName(), borrowingHistories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES BY USER ID AND BOOK TITLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Borrowing> getBorrowingDetailById(String borrowingId) {
        ResponseInfo<Borrowing> responseInfo = new ResponseInfo<>();

        try {
            Borrowing borrowingDetail;
            borrowingDetail = borrowingDetailRepository.getBorrowingDetailById(borrowingId);
            responseInfo.setSuccess(borrowingDetail);
            log.info("[{}][SUCCESS GET BORROWING DETAIL][ID: {}]", getClass().getSimpleName(), borrowingId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BORROWING DETAIL][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), borrowingId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Integer> getCountBorrowingStatusByUserId(String userId) {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            String borrowingStatus = applicationProperties.getBorrowedStatus();
            count = borrowingDetailRepository.getCountBorrowingStatusByUserId(userId, borrowingStatus);
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT BORROWING STATUS][USER ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT BORROWING STATUS][USER ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Integer> getCountAllBorrowingHistoryByUserId(String userId) {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            count = borrowingDetailRepository.getCountAllBorrowingHistoryByUserId(userId);
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT RETURN STATUS][USER ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT RETURN STATUS][USER ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Borrowing> addNewBorrowingDetail(BorrowingDetailRq borrowingDetailRq) {
        ResponseInfo<Borrowing> responseInfo = new ResponseInfo<>();

        try {
            Borrowing borrowingDetail;
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
            Borrowing borrowingDetail = borrowingDetailRepository.getBorrowingDetailById(updateBorrowingDetailRq.getBorrowingId());
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
