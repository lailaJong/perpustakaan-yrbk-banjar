package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.config.properties.ApplicationProperties;
import tugasakhir.library.model.dto.OrderDetail;
import tugasakhir.library.model.dto.OrderDetailOfficer;
import tugasakhir.library.model.entity.BookStock;
import tugasakhir.library.model.entity.Borrowing;
import tugasakhir.library.model.entity.Order;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.bookstock.UpdateBookStockRq;
import tugasakhir.library.model.request.borrowingdetail.BorrowingDetailRq;
import tugasakhir.library.model.request.orderdetail.OrderDetailRq;
import tugasakhir.library.model.request.orderdetail.UpdateOrderDetailRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.BookStockRepository;
import tugasakhir.library.repository.BorrowingDetailRepository;
import tugasakhir.library.repository.OrderDetailRepository;
import tugasakhir.library.utils.bookstock.BookStockMapperImpl;
import tugasakhir.library.utils.borrowingdetail.BorrowingDetailMapperImpl;
import tugasakhir.library.utils.orderdetail.OrderDetailMapperImpl;
import tugasakhir.library.utils.orderdetail.TakingDate;
import tugasakhir.library.utils.validation.BenefitValidation;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class OrderDetailUsecase {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private BorrowingDetailRepository borrowingDetailRepository;
    @Autowired
    private BookStockRepository bookStockRepository;
    @Autowired
    private BenefitValidation benefitValidation;
    @Autowired
    private ApplicationProperties applicationProperties;

    //get order details officer
    public ResponseInfo<List<OrderDetailOfficer>> getAllOrderDetailsOfficer() {
        ResponseInfo<List<OrderDetailOfficer>> responseInfo = new ResponseInfo<>();

        try {
            List<OrderDetailOfficer> orderDetails;
            orderDetails = orderDetailRepository.getAllOrderDetailsOfficer();
            responseInfo.setSuccess(orderDetails);
            log.info("[{}][SUCCESS GET ALL ORDER DETAILS OFFICER][DATA SIZE: {}]", getClass().getSimpleName(), orderDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL ORDER DETAILS OFFICER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    //get order details based on user id
    public ResponseInfo<List<OrderDetail>> getAllOrderDetailsByUserId(String userId) {
        ResponseInfo<List<OrderDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<OrderDetail> orderDetails;
            orderDetails = orderDetailRepository.getAllOrderDetailsByUserId(userId);
            responseInfo.setSuccess(orderDetails);
            log.info("[{}][SUCCESS GET ALL ORDER DETAILS BY USER ID][DATA SIZE: {}]", getClass().getSimpleName(), orderDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL ORDER DETAILS BY USER ID][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    //get order details based on user id and book title
    public ResponseInfo<List<OrderDetail>> getAllOrderDetailsByUserIdAndBookTitle(String userId, String bookTitle) {
        ResponseInfo<List<OrderDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<OrderDetail> orderDetails;
            orderDetails = orderDetailRepository.getAllOrderDetailsByUserIdAndBookTitle(userId, bookTitle);
            responseInfo.setSuccess(orderDetails);
            log.info("[{}][SUCCESS GET ALL ORDER DETAILS BY USER ID AND BOOK TITLE][DATA SIZE: {}]", getClass().getSimpleName(), orderDetails.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL ORDER DETAILS BY USER ID AND BOOK TITLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Order> getOrderDetailById(String orderId) {
        ResponseInfo<Order> responseInfo = new ResponseInfo<>();

        try {
            Order orderDetail;
            orderDetail = orderDetailRepository.getOrderDetailById(orderId);
            responseInfo.setSuccess(orderDetail);
            log.info("[{}][SUCCESS GET ORDER DETAIL][ID: {}]", getClass().getSimpleName(), orderId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ORDER DETAIL][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), orderId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Integer> getCountOrderDetailByUserId(String userId) {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = orderDetailRepository.getCountOrderDetailByUserId(userId);
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT ORDER DETAIL][USER ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT ORDER DETAIL][USER ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Order> addNewOrderDetail(OrderDetailRq orderDetailRq) {
        ResponseInfo<Order> responseInfo = new ResponseInfo<>();

        try {
            //tambahkan validasi check sisa quota
            boolean isQuotasAvailable;
            isQuotasAvailable = benefitValidation.isQuotasAvailable(orderDetailRq.getUserId());
            if (isQuotasAvailable){
                Order orderDetail;
                String id = orderDetailRepository.generateOrderDetailId();
                orderDetailRq.setOrderDate(new Date());
                orderDetailRq.setTakingDate(TakingDate.setTakingDates(orderDetailRq.getOrderDate()));
                orderDetailRq.setStatus(applicationProperties.getOrderedStatus());
                orderDetail = OrderDetailMapperImpl.toOrderDetail(orderDetailRq, id);
                orderDetailRepository.addOrderDetail(orderDetail);
                //kurangi stok buku by book id
                BookStock bookStock = bookStockRepository.getBookStockByBookId(orderDetailRq.getBookId());
                if (bookStock != null) {
                    log.info("AVAILABLE TO ORDER: {} STOCK", bookStock.getStock());
                    UpdateBookStockRq updateBookStockRq = null;
                    updateBookStockRq.setBookStockId(bookStock.getBookStockId());
                    updateBookStockRq.setBookId(bookStock.getBookId());
                    updateBookStockRq.setStock((bookStock.getStock()-1));
                    BookStockMapperImpl.updateBookStockFromUpdateBookStockRq(updateBookStockRq, bookStock);
                    bookStockRepository.updateBookStock(bookStock);
                } else {
                    throw new NotFoundException("Data of the book stock is not found");
                }
                responseInfo.setSuccess(orderDetail);
                log.info("[{}][SUCCESS ADD NEW ORDER DETAIL]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError("Quota is not available, remaining quota is 0!");
                log.info("[{}][FAILED ADD NEW ORDER DETAIL]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW ORDER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateOrderDetail(UpdateOrderDetailRq updateOrderDetailRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Order orderDetail = orderDetailRepository.getOrderDetailById(updateOrderDetailRq.getOrderId());
            if (orderDetail != null) {
                if (updateOrderDetailRq.getStatus().equalsIgnoreCase(applicationProperties.getCancelledStatus())){
                    //tambahkan stok buku by book id
                    BookStock bookStock = bookStockRepository.getBookStockByBookId(updateOrderDetailRq.getBookId());
                    if (bookStock != null) {
                        UpdateBookStockRq updateBookStockRq = null;
                        updateBookStockRq.setBookStockId(bookStock.getBookStockId());
                        updateBookStockRq.setBookId(bookStock.getBookId());
                        updateBookStockRq.setStock((bookStock.getStock()+1));
                        BookStockMapperImpl.updateBookStockFromUpdateBookStockRq(updateBookStockRq, bookStock);
                        bookStockRepository.updateBookStock(bookStock);
                    } else {
                        throw new NotFoundException("Data of the book stock is not found");
                    }
                }else {
                        //tambahkan borrowing detail
                        Borrowing borrowingDetail;
                        BorrowingDetailRq borrowingDetailRq = new BorrowingDetailRq();
                        borrowingDetailRq.setBorrowingId(borrowingDetailRepository.generateBorrowingDetailId());
                        borrowingDetailRq.setUserId(updateOrderDetailRq.getUserId());
                        borrowingDetailRq.setBookId(updateOrderDetailRq.getBookId());
                        borrowingDetailRq.setBorrowingDate(new Date());
                        borrowingDetailRq.setReturnDate(benefitValidation.setReturnDates(updateOrderDetailRq.getUserId()));
                        borrowingDetailRq.setStatus(applicationProperties.getBorrowedStatus());
                        borrowingDetail = BorrowingDetailMapperImpl.toBorrowingDetail(borrowingDetailRq);
                        borrowingDetailRepository.addBorrowingDetail(borrowingDetail);
                        responseInfo.setSuccess(borrowingDetail);
                }
                OrderDetailMapperImpl.updateOrderDetailFromUpdateOrderDetailRq(updateOrderDetailRq, orderDetail);
                orderDetailRepository.updateOrderDetail(orderDetail);

                responseInfo.setSuccess("Order is not found");
                log.info("[{}][SUCCESS UPDATE ORDER DETAIL]", getClass().getSimpleName());
            } else {
                throw new NotFoundException(updateOrderDetailRq.getOrderId() + " IS NOT FOUND");
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE ORDER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteOrderDetail(String orderId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            orderDetailRepository.deleteOrderDetail(orderId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE ORDER DETAIL][{}]", getClass().getSimpleName(), orderId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE ORDER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

//    public ResponseInfo<List<Order>> getAllOrderDetails() {
//        ResponseInfo<List<Order>> responseInfo = new ResponseInfo<>();
//
//        try {
//            List<Order> orderDetails;
//            orderDetails = orderDetailRepository.getAllOrderDetails();
//            orderDetails.addAll(orderDetailRepository.getAllOrderDetails());
//            responseInfo.setSuccess(orderDetails);
//            log.info("[{}][SUCCESS GET ALL ORDER DETAIL][DATA SIZE: {}]", getClass().getSimpleName(), orderDetails.size());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET ALL ORDER DETAIL][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }
}
