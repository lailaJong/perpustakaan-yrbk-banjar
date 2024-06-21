package tugasakhir.library.usecase;

import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.book.BookRq;
import tugasakhir.library.model.request.book.UpdateBookRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.BookRepository;
import tugasakhir.library.utils.book.BooksMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Slf4j
public class BookUsecase {
    @Autowired
    private BookRepository bookRepository;

    public ResponseInfo<List<Book>> getAllBooks() {
        ResponseInfo<List<Book>> responseInfo = new ResponseInfo<>();

        try {
            List<Book> books;
            books = bookRepository.getAllBooks();
            books.addAll(bookRepository.getAllBooks());
            responseInfo.setSuccess(books);
            log.info("[{}][SUCCESS GET ALL BOOK][DATA SIZE: {}]", getClass().getSimpleName(), books.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<Book>> getAllBooks(String status) {
        ResponseInfo<List<Book>> responseInfo = new ResponseInfo<>();

        try {
            List<Book> books;
            books = bookRepository.getAllBooks(status);
            books.addAll(bookRepository.getAllBooks(status));
            responseInfo.setSuccess(books);
            log.info("[{}][SUCCESS GET ALL BOOK][DATA SIZE: {}]", getClass().getSimpleName(), books.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Book> getBookById(String bookId) {
        ResponseInfo<Book> responseInfo = new ResponseInfo<>();

        try {
            Book book;
            book = bookRepository.getBookById(bookId);
            responseInfo.setSuccess(book);
            log.info("[{}][SUCCESS GET BOOK][ID: {}]", getClass().getSimpleName(), bookId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BOOK][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), bookId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Book> getBookByTitle(String bookTitle) {
        ResponseInfo<Book> responseInfo = new ResponseInfo<>();

        try {
            Book book;
            book = bookRepository.getBookByBookTitle(bookTitle);
            responseInfo.setSuccess(book);
            log.info("[{}][SUCCESS GET BOOK][TITLE: {}]", getClass().getSimpleName(), bookTitle);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BOOK][TITLE: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), bookTitle, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Book> addNewBook(BookRq bookRq) {
        ResponseInfo<Book> responseInfo = new ResponseInfo<>();

        try {
            Book book;
            bookRq.setBookId(bookRepository.generateBookId());
            book = BooksMapper.INSTANCE.toBook(bookRq);
            bookRepository.addBook(book);
            responseInfo.setSuccess(book);
            log.info("[{}][SUCCESS ADD NEW BOOK]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBook(UpdateBookRq updateBooksRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Book book = bookRepository.getBookById(updateBooksRq.getBookId());
            if (book != null) {
                BooksMapper.INSTANCE.updateBookFromUpdateBookRq(updateBooksRq, book);
                bookRepository.updateBook(book);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE BOOK]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteBook(String bookId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            bookRepository.deleteBook(bookId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE BOOK][{}]", getClass().getSimpleName(), bookId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
