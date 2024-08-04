package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.dto.BookStockDetail;
import tugasakhir.library.model.entity.BookStock;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.bookstock.BookStockRq;
import tugasakhir.library.model.request.bookstock.UpdateBookStockRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.BookStockRepository;
import tugasakhir.library.utils.bookstock.BookStockMapperImpl;

import java.util.List;

@Component
@Slf4j
public class BookStockUsecase {
    @Autowired
    private BookStockRepository bookStockRepository;

//    public ResponseInfo<List<BookStock>> getAllBookStocks() {
//        ResponseInfo<List<BookStock>> responseInfo = new ResponseInfo<>();
//
//        try {
//            List<BookStock> bookStocks;
//            bookStocks = bookStockRepository.getAllBookStocks();
//            bookStocks.addAll(bookStockRepository.getAllBookStocks());
//            responseInfo.setSuccess(bookStocks);
//            log.info("[{}][SUCCESS GET ALL BOOK STOCK][DATA SIZE: {}]", getClass().getSimpleName(), bookStocks.size());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET ALL BOOK STOCK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }

    public ResponseInfo<List<BookStockDetail>> getAllBookStockDetails() {
        ResponseInfo<List<BookStockDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BookStockDetail> bookStocks;
            bookStocks = bookStockRepository.getAllBookStockDetails();
            responseInfo.setSuccess(bookStocks);
            log.info("[{}][SUCCESS GET ALL BOOK STOCK DETAILS][DATA SIZE: {}]", getClass().getSimpleName(), bookStocks.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK STOCK DETAILS][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BookStockDetail>> getAllBookStockDetailsByBookTitle(String bookTitle) {
        ResponseInfo<List<BookStockDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BookStockDetail> bookStocks;
            bookStocks = bookStockRepository.getAllBookStockDetailsByBookTitle(bookTitle);
            responseInfo.setSuccess(bookStocks);
            log.info("[{}][SUCCESS GET ALL BOOK STOCK DETAILS BY BOOK TITLE][{}][DATA SIZE: {}]", getClass().getSimpleName(), bookTitle, bookStocks.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK STOCK DETAILS BY BOOK TITLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BookStock> getBookStockById(String bookStockId) {
        ResponseInfo<BookStock> responseInfo = new ResponseInfo<>();

        try {
            BookStock bookStock;
            bookStock = bookStockRepository.getBookStockById(bookStockId);
            responseInfo.setSuccess(bookStock);
            log.info("[{}][SUCCESS GET BOOK STOCK][ID: {}]", getClass().getSimpleName(), bookStockId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BOOK STOCK][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), bookStockId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BookStock> addNewBookStock(BookStockRq bookStockRq) {
        ResponseInfo<BookStock> responseInfo = new ResponseInfo<>();

        try {
            BookStock bookStock;
            if (bookStockRepository.getBookStockByBookId(bookStockRq.getBookId()) == null) {
                String id = bookStockRepository.generateBookStockId();
                bookStock = BookStockMapperImpl.toBookStock(bookStockRq, id);
                bookStockRepository.addBookStock(bookStock);
                responseInfo.setSuccess(bookStock);
                log.info("[{}][SUCCESS ADD NEW BOOK STOCK]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(bookStockRq.getBookId() + " is already exist");
                log.info("[{}][FAILED ADD NEW BOOK STOCK]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BOOK STOCK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBookStock(UpdateBookStockRq updateBookStockRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            boolean isEmpty = bookStockRepository.existsByBookStockId(updateBookStockRq.getBookStockId());
            if (!isEmpty) {
                BookStock bookStock = bookStockRepository.getBookStockById(updateBookStockRq.getBookStockId());
                BookStockMapperImpl.updateBookStockFromUpdateBookStockRq(updateBookStockRq, bookStock);
                bookStockRepository.updateBookStock(bookStock);

                responseInfo.setSuccess();
                log.info("[{}][SUCCESS UPDATE BOOK STOCK]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(updateBookStockRq.getBookStockId() + " is not exist");
                log.info("[{}][FAILED UPDATE BOOK STOCK]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BOOK STOCK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteBookStock(String bookStockId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            boolean isEmpty = bookStockRepository.existsByBookStockId(bookStockId);
            if (!isEmpty) {
                bookStockRepository.deleteBookStock(bookStockId);
                responseInfo.setSuccess();
                log.info("[{}][SUCCESS DELETE BOOK STOCK][{}]", getClass().getSimpleName(), bookStockId);
            } else {
                responseInfo.setBussinessError(bookStockId + " is not exist");
                log.info("[{}][FAILED DELETE BOOK STOCK]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE BOOK STOCK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

}
