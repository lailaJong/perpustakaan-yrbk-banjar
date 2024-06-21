package tugasakhir.library.utils.borrowingdetail;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.BorrowingDetail;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.borrowingdetail.BorrowingDetailRq;
import tugasakhir.library.model.request.borrowingdetail.UpdateBorrowingDetailRq;
import tugasakhir.library.model.request.orderdetail.UpdateOrderDetailRq;

import java.util.Date;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class BorrowingDetailMapperImpl implements BorrowingDetailMapper {

    @Override
    public BorrowingDetail toBorrowingDetail(BorrowingDetailRq borrowingDetailRq) {
        if (borrowingDetailRq == null) {
            return null;
        }
        BorrowingDetail borrowingDetail = new BorrowingDetail();
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
    public void updateBorrowingDetailFromUpdateBorrowingDetailRq(UpdateBorrowingDetailRq updateOrderDetailRq, BorrowingDetail borrowingDetail) {
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