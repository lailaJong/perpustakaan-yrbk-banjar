package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.config.properties.ApplicationProperties;
import tugasakhir.library.model.dto.BorrowingDetail;
import tugasakhir.library.model.dto.BorrowingHistories;
import tugasakhir.library.model.dto.BorrowingHistoriesUser;
import tugasakhir.library.model.dto.BorrowingTrxOfficer;
import tugasakhir.library.model.entity.BookStock;
import tugasakhir.library.model.entity.Borrowing;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.bookstock.UpdateBookStockRq;
import tugasakhir.library.model.request.borrowingdetail.BorrowingDetailRq;
import tugasakhir.library.model.request.borrowingdetail.UpdateBorrowingDetailRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.BookStockRepository;
import tugasakhir.library.repository.BorrowingDetailRepository;
import tugasakhir.library.repository.MemberRepository;
import tugasakhir.library.repository.MemberStatusRepository;
import tugasakhir.library.utils.bookstock.BookStockMapperImpl;
import tugasakhir.library.utils.borrowingdetail.BorrowingDetailMapperImpl;
import tugasakhir.library.utils.scoredetail.ScoreDetailMapperImpl;
import tugasakhir.library.utils.validation.BenefitValidation;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class BorrowingDetailUsecase {
    @Autowired
    private BorrowingDetailRepository borrowingDetailRepository;
    @Autowired
    private BookStockRepository bookStockRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberStatusRepository memberStatusRepository;
    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private BenefitValidation benefitValidation;

    public ResponseInfo<List<BorrowingDetail>> getAllBorrowingDetailsByUserId(String userId) {
        ResponseInfo<List<BorrowingDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingDetail> borrowingDetails;
            borrowingDetails = borrowingDetailRepository.getAllBorrowingDetailsByUserId(userId);
            responseInfo.setSuccess(borrowingDetails);
            log.info("[{}][SUCCESS GET ALL BORROWING DETAILS BY USER ID][DATA SIZE: {}]", getClass().getSimpleName(), borrowingDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING DETAILS BY USER ID][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<BorrowingDetail>> getAllBorrowingDetailsByUserIdAndBookTitle(String userId, String bookTitle) {
        ResponseInfo<List<BorrowingDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingDetail> borrowingDetails;
            borrowingDetails = borrowingDetailRepository.getAllBorrowingDetailsByUserIdAndBookTitle(userId, bookTitle);
            responseInfo.setSuccess(borrowingDetails);
            log.info("[{}][SUCCESS GET ALL BORROWING DETAILS BY USER ID AND BOOK TITLE][DATA SIZE: {}]", getClass().getSimpleName(), borrowingDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING DETAILS BY USER ID AND BOOK TITLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BorrowingHistories>> getAllBorrowingHistories() {
        ResponseInfo<List<BorrowingHistories>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingHistories> borrowingHistories;
            borrowingHistories = borrowingDetailRepository.getAllBorrowingHistories();
            responseInfo.setSuccess(borrowingHistories);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES][DATA SIZE: {}]", getClass().getSimpleName(), borrowingHistories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<BorrowingHistories>> getAllBorrowingHistoriesByMemberName(String name) {
        ResponseInfo<List<BorrowingHistories>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingHistories> borrowingHistories;
            borrowingHistories = borrowingDetailRepository.getAllBorrowingHistoriesByMemberName(name);
            responseInfo.setSuccess(borrowingHistories);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES BY NAME][DATA SIZE: {}]", getClass().getSimpleName(), borrowingHistories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES BY NAME][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<BorrowingTrxOfficer>> getAllBorrowingTrx() {
        ResponseInfo<List<BorrowingTrxOfficer>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingTrxOfficer> borrowingTrxOfficers;
            borrowingTrxOfficers = borrowingDetailRepository.getAllBorrowingTrx();
            responseInfo.setSuccess(borrowingTrxOfficers);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES][DATA SIZE: {}]", getClass().getSimpleName(), borrowingTrxOfficers.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<BorrowingTrxOfficer>> getAllBorrowingTrxByMemberName(String name) {
        ResponseInfo<List<BorrowingTrxOfficer>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingTrxOfficer> borrowingTrxOfficers;
            borrowingTrxOfficers = borrowingDetailRepository.getAllBorrowingTrxByMemberName(name);
            responseInfo.setSuccess(borrowingTrxOfficers);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES BY NAME][DATA SIZE: {}]", getClass().getSimpleName(), borrowingTrxOfficers.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES BY NAME][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<BorrowingTrxOfficer>> getAllLateBorrowingTrx() {
        ResponseInfo<List<BorrowingTrxOfficer>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingTrxOfficer> borrowingTrxOfficers;
            borrowingTrxOfficers = borrowingDetailRepository.getAllLateBorrowingTrx();
            responseInfo.setSuccess(borrowingTrxOfficers);
            log.info("[{}][SUCCESS GET ALL LATE BORROWING HISTORIES][DATA SIZE: {}]", getClass().getSimpleName(), borrowingTrxOfficers.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL LATE BORROWING HISTORIES][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<BorrowingTrxOfficer>> getAllLateBorrowingTrxByMemberName(String name) {
        ResponseInfo<List<BorrowingTrxOfficer>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingTrxOfficer> borrowingTrxOfficers;
            borrowingTrxOfficers = borrowingDetailRepository.getAllLateBorrowingTrxByMemberName(name);
            responseInfo.setSuccess(borrowingTrxOfficers);
            log.info("[{}][SUCCESS GET ALL LATE BORROWING HISTORIES BY NAME][DATA SIZE: {}]", getClass().getSimpleName(), borrowingTrxOfficers.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL LATE BORROWING HISTORIES BY NAME][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BorrowingHistoriesUser>> getAllBorrowingHistoriesByUserId(String userId) {
        ResponseInfo<List<BorrowingHistoriesUser>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingHistoriesUser> borrowingHistories;
            borrowingHistories = borrowingDetailRepository.getAllBorrowingHistoriesByUserId(userId);
            responseInfo.setSuccess(borrowingHistories);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES BY USER ID][DATA SIZE: {}]", getClass().getSimpleName(), borrowingHistories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES BY USER ID][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BorrowingHistoriesUser>> getAllBorrowingHistoriesByUserIdAndBookTitle(String userId, String bookTitle) {
        ResponseInfo<List<BorrowingHistoriesUser>> responseInfo = new ResponseInfo<>();

        try {
            List<BorrowingHistoriesUser> borrowingHistories;
            borrowingHistories = borrowingDetailRepository.getAllBorrowingHistoriesByUserIdAndBookTitle(userId, bookTitle);
            responseInfo.setSuccess(borrowingHistories);
            log.info("[{}][SUCCESS GET ALL BORROWING HISTORIES BY USER ID AND BOOK TITLE][DATA SIZE: {}]", getClass().getSimpleName(), borrowingHistories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BORROWING HISTORIES BY USER ID AND BOOK TITLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Integer> getCountBorrowingStatusByUserId(String userId) {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            count = borrowingDetailRepository.getCountBorrowingStatusByUserId(userId);
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT BORROWING STATUS][USER ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT BORROWING STATUS][USER ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.handleException(ex);
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
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Integer> getCountAllBorrowing() {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            count = borrowingDetailRepository.getCountAllBorrowing();
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT ALL BORROWING]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT ALL BORROWING][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Integer> getCountAllLateBorrowing() {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            count = borrowingDetailRepository.getCountAllLateBorrowing();
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT ALL LATE BORROWING]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT ALL LATE BORROWING][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Borrowing> addNewBorrowingDetail(BorrowingDetailRq borrowingDetailRq) {
        ResponseInfo<Borrowing> responseInfo = new ResponseInfo<>();

        try {
            //tambahkan validasi check sisa quota
            boolean isQuotasAvailable = false;
            isQuotasAvailable = benefitValidation.isQuotasAvailable(borrowingDetailRq.getUserId());
            BookStock bookStock = bookStockRepository.getBookStockByBookId(borrowingDetailRq.getBookId());
            if (isQuotasAvailable && bookStock != null){
                Borrowing borrowingDetail;
                borrowingDetailRq.setBorrowingId(borrowingDetailRepository.generateBorrowingDetailId());
                borrowingDetailRq.setBorrowingDate(new Date());
                borrowingDetailRq.setReturnDate(benefitValidation.setReturnDates(borrowingDetailRq.getUserId()));
                borrowingDetailRq.setStatus(applicationProperties.getBorrowedStatus());
                borrowingDetail = BorrowingDetailMapperImpl.toBorrowingDetail(borrowingDetailRq);
                borrowingDetailRepository.addBorrowingDetail(borrowingDetail);
                responseInfo.setSuccess(borrowingDetail);
                log.info("[{}][SUCCESS ADD NEW BORROWING DETAIL]", getClass().getSimpleName());
                //kurangi stok buku by book id
                log.info("AVAILABLE TO ORDER: {} STOCK", bookStock.getStock());
                UpdateBookStockRq updateBookStockRq = null;
                updateBookStockRq.setBookStockId(bookStock.getBookStockId());
                updateBookStockRq.setBookId(bookStock.getBookId());
                updateBookStockRq.setStock((bookStock.getStock()-1));
                BookStockMapperImpl.updateBookStockFromUpdateBookStockRq(updateBookStockRq, bookStock);
                bookStockRepository.updateBookStock(bookStock);
                log.info("[SUCCESS UPDATE BOOK STOCK][STOCK: {}]", bookStock.getStock()-1);
            } else {
                if (!isQuotasAvailable){
                    responseInfo.setBussinessError("Quota is not available, remaining quota is 0!");
                    log.info("[{}][FAILED ADD NEW ORDER DETAIL]", getClass().getSimpleName());
                } else {
                    responseInfo.setBussinessError("Data of the book stock is not found!");
                    log.info("[{}][FAILED ADD NEW ORDER DETAIL]", getClass().getSimpleName());
                }
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BORROWING DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBorrowingDetail(UpdateBorrowingDetailRq updateBorrowingDetailRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();
        int finalPoint = 0;
        try {
            boolean isExist = borrowingDetailRepository.existsByBorrowingId(updateBorrowingDetailRq.getBorrowingId());
            if (isExist){
            Borrowing borrowingDetail = borrowingDetailRepository.getBorrowingDetailById(updateBorrowingDetailRq.getBorrowingId());
            updateBorrowingDetailRq.setActualReturnDate(new Date());
            Member member = memberRepository.getMemberByUserId(updateBorrowingDetailRq.getUserId());
            BookStock bookStock = bookStockRepository.getBookStockByBookId(updateBorrowingDetailRq.getBookId());
            int previousPoint = member.getPoint();
            int finalStock = bookStock.getStock();
                if (updateBorrowingDetailRq.getStatus().equalsIgnoreCase(applicationProperties.getReturnedStatus())){
                    if (updateBorrowingDetailRq.getActualReturnDate().after(updateBorrowingDetailRq.getReturnDate())){
                        //late case
                        //point -1
                        finalPoint = previousPoint - applicationProperties.getLatePoint();
                    } else {
                        //point +1
                        finalPoint = previousPoint + applicationProperties.getOnTimePoint();
                    }
                    //update stock +1
                    finalStock = finalStock + 1;
                } else {
                    //lost case
                    //update point -10
                    finalPoint = previousPoint - applicationProperties.getLostPoint();
                }
                //kondisi update status member where point <0
                if (finalPoint < 0){
                    member.setMemberStatusId(memberStatusRepository.getMemberStatusByStatus("DEACTIVE").getMemberStatusId());
                }
                //update point in member
                member.setPoint(finalPoint);
                member.setScoreDetailId(ScoreDetailMapperImpl.getScoreDetailId(finalPoint));
                memberRepository.updateMember(member);
                //update book stock
                bookStock.setStock(finalStock);
                bookStockRepository.updateBookStock(bookStock);
                //update borrowing trx
                BorrowingDetailMapperImpl.updateBorrowingDetailFromUpdateBorrowingDetailRq(updateBorrowingDetailRq, borrowingDetail);
                borrowingDetailRepository.updateBorrowingDetail(borrowingDetail);

                responseInfo.setSuccess();
                log.info("[{}][SUCCESS UPDATE BORROWING DETAIL][POINT: {}][STOCK: {}]", getClass().getSimpleName(), finalPoint, finalStock);
            } else {
                responseInfo.setBussinessError(updateBorrowingDetailRq.getBorrowingId() + " is not found!");
                log.info("[{}][FAILED UPDATE BORROWING DETAIL]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BORROWING DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


//    public ResponseInfo<Object> deleteBorrowingDetail(String borrowingId) {
//        ResponseInfo<Object> responseInfo = new ResponseInfo<>();
//
//        try {
//            borrowingDetailRepository.deleteBorrowingDetail(borrowingId);
//            responseInfo.setSuccess();
//            log.info("[{}][SUCCESS DELETE BORROWING DETAIL][{}]", getClass().getSimpleName(), borrowingId);
//        } catch (Exception ex) {
//            log.info("[{}][FAILED DELETE BORROWING DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }

//    public ResponseInfo<Borrowing> getBorrowingDetailById(String borrowingId) {
//        ResponseInfo<Borrowing> responseInfo = new ResponseInfo<>();
//
//        try {
//            Borrowing borrowingDetail;
//            borrowingDetail = borrowingDetailRepository.getBorrowingDetailById(borrowingId);
//            responseInfo.setSuccess(borrowingDetail);
//            log.info("[{}][SUCCESS GET BORROWING DETAIL][ID: {}]", getClass().getSimpleName(), borrowingId);
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET BORROWING DETAIL][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), borrowingId, ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }

//    public ResponseInfo<List<Borrowing>> getAllBorrowingDetails() {
//        ResponseInfo<List<Borrowing>> responseInfo = new ResponseInfo<>();
//
//        try {
//            List<Borrowing> borrowingDetails;
//            borrowingDetails = borrowingDetailRepository.getAllBorrowingDetails();
//            borrowingDetails.addAll(borrowingDetailRepository.getAllBorrowingDetails());
//            responseInfo.setSuccess(borrowingDetails);
//            log.info("[{}][SUCCESS GET ALL BORROWING DETAIL][DATA SIZE: {}]", getClass().getSimpleName(), borrowingDetails.size());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET ALL BORROWING DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }

}
