package tugasakhir.library.utils.borrowingdetail;

import tugasakhir.library.model.entity.Borrowing;
import tugasakhir.library.model.request.borrowingdetail.BorrowingDetailRq;
import tugasakhir.library.model.request.borrowingdetail.UpdateBorrowingDetailRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class BorrowingDetailMapperImpl implements BorrowingDetailMapper {

    @Override
    public Borrowing toBorrowingDetail(BorrowingDetailRq borrowingDetailRq) {
        if (borrowingDetailRq == null) {
            return null;
        }
        Borrowing borrowingDetail = new Borrowing();
        borrowingDetail.setBorrowingId(borrowingDetailRq.getBorrowingId());
        borrowingDetail.setUserId(borrowingDetailRq.getUserId());
        borrowingDetail.setBookId(borrowingDetailRq.getBookId());
        borrowingDetail.setBorrowingDate(borrowingDetailRq.getBorrowingDate());
        borrowingDetail.setReturnDate(borrowingDetailRq.getReturnDate());
        borrowingDetail.setActualReturnDate(borrowingDetailRq.getActualReturnDate());
        borrowingDetail.setStatus(borrowingDetailRq.getStatus());
        return borrowingDetail;
    }

    @Override
    public void updateBorrowingDetailFromUpdateBorrowingDetailRq(UpdateBorrowingDetailRq updateOrderDetailRq, Borrowing borrowingDetail) {
        if ( updateOrderDetailRq == null ) {
            return;
        }

        if ( updateOrderDetailRq.getUserId() != null ) {
            borrowingDetail.setUserId( updateOrderDetailRq.getUserId() );
        }
        if ( updateOrderDetailRq.getBookId() != null ) {
            borrowingDetail.setBookId( updateOrderDetailRq.getBookId() );
        }
        if ( updateOrderDetailRq.getBorrowingDate() != null ) {
            borrowingDetail.setBorrowingDate( updateOrderDetailRq.getBorrowingDate() );
        }
        if ( updateOrderDetailRq.getReturnDate() != null ) {
            borrowingDetail.setReturnDate( updateOrderDetailRq.getReturnDate() );
        }
        if ( updateOrderDetailRq.getActualReturnDate() != null ) {
            borrowingDetail.setActualReturnDate( updateOrderDetailRq.getActualReturnDate() );
        }
        if ( updateOrderDetailRq.getStatus() != null ) {
            borrowingDetail.setStatus( updateOrderDetailRq.getStatus() );
        }
    }
}