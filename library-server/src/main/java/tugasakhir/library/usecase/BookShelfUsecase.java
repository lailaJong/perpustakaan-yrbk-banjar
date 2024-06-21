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
import tugasakhir.library.utils.bookshelf.BookShelfMapper;

import java.util.List;

@Component
@Slf4j
public class BookShelfUsecase {
    @Autowired
    private BookShelfRepository bookShelfRepository;

    public ResponseInfo<List<BookShelf>> getAllBookShelfs() {
        ResponseInfo<List<BookShelf>> responseInfo = new ResponseInfo<>();

        try {
            List<BookShelf> bookShelves;
            bookShelves = bookShelfRepository.getAllBookShelves();
            bookShelves.addAll(bookShelfRepository.getAllBookShelves());
            responseInfo.setSuccess(bookShelves);
            log.info("[{}][SUCCESS GET ALL BOOK SHELF][DATA SIZE: {}]", getClass().getSimpleName(), bookShelves.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK SHELF][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
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
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<BookShelf> addNewBookShelf(BookShelfRq bookShelfRq) {
        ResponseInfo<BookShelf> responseInfo = new ResponseInfo<>();

        try {
            BookShelf bookShelf;
            bookShelfRq.setBookShelfId(bookShelfRepository.generateBookShelfId());
            bookShelf = BookShelfMapper.INSTANCE.toBookShelf(bookShelfRq);
            bookShelfRepository.addBookShelf(bookShelf);
            responseInfo.setSuccess(bookShelf);
            log.info("[{}][SUCCESS ADD NEW BOOK SHELF]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BOOK SHELF][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBookShelf(UpdateBookShelfRq updateBookShelfRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            BookShelf bookShelf = bookShelfRepository.getBookShelfById(updateBookShelfRq.getBookShelfId());
            if (bookShelf != null) {
                BookShelfMapper.INSTANCE.updateBookShelfFromUpdateBookShelfRq(updateBookShelfRq, bookShelf);
                bookShelfRepository.updateBookShelf(bookShelf);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE BOOK SHELF]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BOOK SHELF][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteBookShelf(String bookShelfId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            bookShelfRepository.deleteBookShelf(bookShelfId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE BOOK SHELF][{}]", getClass().getSimpleName(), bookShelfId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE BOOK SHELF][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
