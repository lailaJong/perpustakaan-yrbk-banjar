package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.BookShelf;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.bookshelf.BookShelfRq;
import tugasakhir.library.model.request.bookshelf.UpdateBookShelfRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.BookShelfRepository;
import tugasakhir.library.utils.bookshelf.BookShelfMapperImpl;

import java.util.List;

@Component
@Slf4j
public class BookShelfUsecase {
    @Autowired
    private BookShelfRepository bookShelfRepository;

    public ResponseInfo<List<BookShelf>> getAllBookShelves() {
        ResponseInfo<List<BookShelf>> responseInfo = new ResponseInfo<>();

        try {
            List<BookShelf> bookShelves;
            bookShelves = bookShelfRepository.getAllBookShelves();
            responseInfo.setSuccess(bookShelves);
            log.info("[{}][SUCCESS GET ALL BOOK SHELVES][DATA SIZE: {}]", getClass().getSimpleName(), bookShelves.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK SHELVES][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BookShelf>> getBookShelfByCode(String code) {
        ResponseInfo<List<BookShelf>> responseInfo = new ResponseInfo<>();

        try {
            List <BookShelf> bookShelves;
            bookShelves = bookShelfRepository.getAllBookShelfByCode(code);
            responseInfo.setSuccess(bookShelves);
            log.info("[{}][SUCCESS GET ALL BOOK SHELF][CODE: {}]", getClass().getSimpleName(), code);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK SHELF][CODE: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), code, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BookShelf> getBookShelfById(String bookShelfId) {
        ResponseInfo<BookShelf> responseInfo = new ResponseInfo<>();

        try {
            BookShelf bookShelf;
            bookShelf = bookShelfRepository.getBookShelfById(bookShelfId);
            responseInfo.setSuccess(bookShelf);
            log.info("[{}][SUCCESS GET BOOK SHELF][ID: {}]", getClass().getSimpleName(), bookShelfId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BOOK SHELF][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), bookShelfId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BookShelf> addNewBookShelf(BookShelfRq bookShelfRq) {
        ResponseInfo<BookShelf> responseInfo = new ResponseInfo<>();

        try {
            BookShelf bookShelf;
            boolean isExist = bookShelfRepository.existsByBookShelfCode(bookShelfRq.getBookShelfCode());
            if (!isExist){
                String bookShelfId = bookShelfRepository.generateBookShelfId();
                bookShelf = BookShelfMapperImpl.toBookShelf(bookShelfRq, bookShelfId);
                bookShelfRepository.addBookShelf(bookShelf);
                responseInfo.setSuccess(bookShelf);
                log.info("[{}][SUCCESS ADD NEW BOOK SHELF]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(bookShelfRq.getBookShelfCode() + " is already exist");
                log.info("[{}][FAILED ADD NEW BOOK SHELF]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BOOK SHELF][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBookShelf(UpdateBookShelfRq updateBookShelfRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            boolean isExist = bookShelfRepository.existsByBookShelfId(updateBookShelfRq.getBookShelfId());
            if (isExist) {
                BookShelf bookShelf = bookShelfRepository.getBookShelfById(updateBookShelfRq.getBookShelfId());
                BookShelfMapperImpl.updateBookShelfFromUpdateBookShelfRq(updateBookShelfRq, bookShelf);
                bookShelfRepository.updateBookShelf(bookShelf);

                responseInfo.setSuccess();
                log.info("[{}][SUCCESS UPDATE BOOK SHELF]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(updateBookShelfRq.getBookShelfId() + " is not exist");
                log.info("[{}][FAILED UPDATE BOOK SHELF]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BOOK SHELF][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteBookShelf(String bookShelfId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            boolean isExist = bookShelfRepository.existsByBookShelfId(bookShelfId);
            if (isExist) {
                bookShelfRepository.deleteBookShelf(bookShelfId);
                responseInfo.setSuccess();
                log.info("[{}][SUCCESS DELETE BOOK SHELF][{}]", getClass().getSimpleName(), bookShelfId);
            } else {
                responseInfo.setBussinessError(bookShelfId + " is not exist");
                log.info("[{}][FAILED DELETE BOOK SHELF]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE BOOK SHELF][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

}
