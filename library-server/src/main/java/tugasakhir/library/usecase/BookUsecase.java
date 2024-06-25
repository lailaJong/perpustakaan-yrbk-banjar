package tugasakhir.library.usecase;

import tugasakhir.library.model.dto.BookDetail;
import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.book.BookRq;
import tugasakhir.library.model.request.book.UpdateBookRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.utils.book.BooksMapperImpl;

import java.util.List;

@Component
@Slf4j
public class BookUsecase {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookShelfRepository bookShelfRepository;

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

    public ResponseInfo<BookDetail> getBookById(String bookId) {
        ResponseInfo<BookDetail> responseInfo = new ResponseInfo<>();

        try {
            BookDetail bookDetail;
            bookDetail = bookRepository.getBookDetailById(bookId);
            responseInfo.setSuccess(bookDetail);
            log.info("[{}][SUCCESS GET BOOK][ID: {}]", getClass().getSimpleName(), bookId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BOOK][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), bookId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<Book>> getBookByTitle(String bookTitle) {
        ResponseInfo<List<Book>> responseInfo = new ResponseInfo<>();

        try {
            List<Book> books;
            books = bookRepository.getBookByBookTitle(bookTitle);
            responseInfo.setSuccess(books);
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
            bookRq.setBookId(bookRepository.generateBookId());
            String authorId = authorRepository.getAuthorByName(bookRq.getAuthorName()).getAuthorId();
            String categoryId = categoryRepository.getCategoryByName(bookRq.getCategoryName()).getCategoryId();
            String publisherId = publisherRepository.getPublisherByName(bookRq.getPublisherName()).getPublisherId();
            String bookShelfId = bookShelfRepository.getBookShelfByCode(bookRq.getBookShelfCode()).getBookShelfId();

            Book book = BooksMapperImpl.toBook(bookRq, categoryId, publisherId, authorId, bookShelfId);
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
                String authorId = authorRepository.getAuthorByName(updateBooksRq.getAuthorName()).getAuthorId();
                String categoryId = categoryRepository.getCategoryByName(updateBooksRq.getCategoryName()).getCategoryId();
                String publisherId = publisherRepository.getPublisherByName(updateBooksRq.getPublisherName()).getPublisherId();
                String bookShelfId = bookShelfRepository.getBookShelfByCode(updateBooksRq.getBookShelfCode()).getBookShelfId();

                BooksMapperImpl.updateBookFromUpdateBookRq(updateBooksRq, book, categoryId, publisherId, authorId, bookShelfId);
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
