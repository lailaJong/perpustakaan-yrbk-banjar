package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.BookShelf;
import tugasakhir.library.model.entity.BookStock;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.bookshelf.BookShelfRq;
import tugasakhir.library.model.request.bookshelf.UpdateBookShelfRq;
import tugasakhir.library.model.request.bookstock.BookStockRq;
import tugasakhir.library.model.request.bookstock.UpdateBookStockRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.BookStockRepository;
import tugasakhir.library.utils.bookshelf.BookShelfMapper;
import tugasakhir.library.utils.bookstock.BookStockMapper;

import java.util.List;

@Component
@Slf4j
public class BookStockUsecase {
    @Autowired
    private BookStockRepository bookStockRepository;

    public ResponseInfo<List<BookStock>> getAllBookStocks() {
        ResponseInfo<List<BookStock>> responseInfo = new ResponseInfo<>();

        try {
            List<BookStock> bookStocks;
            bookStocks = bookStockRepository.getAllBookStocks();
            bookStocks.addAll(bookStockRepository.getAllBookStocks());
            responseInfo.setSuccess(bookStocks);
            log.info("[{}][SUCCESS GET ALL BOOK STOCK][DATA SIZE: {}]", getClass().getSimpleName(), bookStocks.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK STOCK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
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
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BookStock> addNewBookStock(BookStockRq bookStockRq) {
        ResponseInfo<BookStock> responseInfo = new ResponseInfo<>();

        try {
            BookStock bookStock;
            bookStockRq.setBookStockId(bookStockRepository.generateBookStockId());
            bookStock = BookStockMapper.INSTANCE.toBookStock(bookStockRq);
            bookStockRepository.addBookStock(bookStock);
            responseInfo.setSuccess(bookStock);
            log.info("[{}][SUCCESS ADD NEW BOOK STOCK]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BOOK STOCK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBookStock(UpdateBookStockRq updateBookStockRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            BookStock bookStock = bookStockRepository.getBookStockById(updateBookStockRq.getBookStockId());
            if (bookStock != null) {
                BookStockMapper.INSTANCE.updateBookStockFromUpdateBookStockRq(updateBookStockRq, bookStock);
                bookStockRepository.updateBookStock(bookStock);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE BOOK STOCK]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BOOK STOCK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteBookStock(String bookStockId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            bookStockRepository.deleteBookStock(bookStockId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE BOOK STOCK][{}]", getClass().getSimpleName(), bookStockId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE BOOK STOCK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
