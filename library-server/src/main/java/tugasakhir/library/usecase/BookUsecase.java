package tugasakhir.library.usecase;

import tugasakhir.library.model.dto.BookDetail;
import tugasakhir.library.model.dto.ListBook;
import tugasakhir.library.model.dto.TopBorrowedBook;
import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.book.BookRq;
import tugasakhir.library.model.request.book.UpdateBookRq;
import tugasakhir.library.model.request.bookstock.BookStockRq;
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
    @Autowired
    private BookStockUsecase bookStockUsecase;

    public ResponseInfo<List<BookDetail>> getAllBooksDetail() {
        ResponseInfo<List<BookDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BookDetail> books;
            books = bookRepository.getAllBooksDetail();
            responseInfo.setSuccess(books);
            log.info("[{}][SUCCESS GET ALL BOOK][DATA SIZE: {}]", getClass().getSimpleName(), books.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<BookDetail>> getBookByTitle(String bookTitle) {
        ResponseInfo<List<BookDetail>> responseInfo = new ResponseInfo<>();

        try {
            List<BookDetail> books;
            books = bookRepository.getBooksByBookTitle(bookTitle);
            responseInfo.setSuccess(books);
            log.info("[{}][SUCCESS GET ALL BOOK][DATA SIZE: {}]", getClass().getSimpleName(), books.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET BOOK DETAIL][TITLE: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), bookTitle, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<ListBook>> getAllBookTitles() {
        ResponseInfo<List<ListBook>> responseInfo = new ResponseInfo<>();

        try {
            List<ListBook> books;
            books = bookRepository.getAllBookTitles();
            responseInfo.setSuccess(books);
            log.info("[{}][SUCCESS GET ALL BOOK NAMES][DATA SIZE: {}]", getClass().getSimpleName(), books.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL BOOK NAMES][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Integer> getCountAllBooks() {
        ResponseInfo<Integer> responseInfo = new ResponseInfo<>();

        try {
            int count = 0;
            count = bookRepository.getCountAllBook();
            responseInfo.setSuccess(count);
            log.info("[{}][SUCCESS GET COUNT ALL BOOK]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET COUNT BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<List<TopBorrowedBook>> getTopBorrowedBooks() {
        ResponseInfo<List<TopBorrowedBook>> responseInfo = new ResponseInfo<>();

        try {
            List<TopBorrowedBook> books;
            books = bookRepository.getTopBorrowedBook();
            responseInfo.setSuccess(books);
            log.info("[{}][SUCCESS GET TOP BORROWED BOOKS][DATA SIZE: {}]", getClass().getSimpleName(), books.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET TOP BORROWED BOOKS][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
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
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Book> addNewBook(BookRq bookRq) {
        ResponseInfo<Book> responseInfo = new ResponseInfo<>();

        try {
            boolean isExist = bookRepository.existsByBookTitle(bookRq.getBookTitle());
            if (!isExist){
                String bookId = bookRepository.generateBookId();
                String authorId = authorRepository.getAuthorByName(bookRq.getAuthorName()).getAuthorId();
                String categoryId = categoryRepository.getCategoryByName(bookRq.getCategoryName()).getCategoryId();
                String publisherId = publisherRepository.getPublisherByName(bookRq.getPublisherName()).getPublisherId();
                String bookShelfId = bookShelfRepository.getBookShelfByCode(bookRq.getBookShelfCode()).getBookShelfId();

                Book book = BooksMapperImpl.toBook(bookRq, categoryId, publisherId, authorId, bookShelfId, bookId);
                bookRepository.addBook(book);

                BookStockRq bookStockRq = new BookStockRq()
                        .setBookId(bookId)
                        .setStock(bookRq.getStock());
                bookStockUsecase.addNewBookStock(bookStockRq);
                responseInfo.setSuccess(book);
                log.info("[{}][SUCCESS ADD NEW BOOK]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(bookRq.getBookTitle() + " is already exist");
                log.info("[{}][FAILED ADD NEW BOOK]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateBook(UpdateBookRq updateBooksRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            boolean isExist = bookRepository.existsByBookId(updateBooksRq.getBookId());
            if (isExist) {
                Book book = bookRepository.getBookById(updateBooksRq.getBookId());
                String authorId = authorRepository.getAuthorByName(updateBooksRq.getAuthorName()).getAuthorId();
                String categoryId = categoryRepository.getCategoryByName(updateBooksRq.getCategoryName()).getCategoryId();
                String publisherId = publisherRepository.getPublisherByName(updateBooksRq.getPublisherName()).getPublisherId();
                String bookShelfId = bookShelfRepository.getBookShelfByCode(updateBooksRq.getBookShelfCode()).getBookShelfId();

                BooksMapperImpl.updateBookFromUpdateBookRq(updateBooksRq, book, categoryId, publisherId, authorId, bookShelfId);
                bookRepository.updateBook(book);

                responseInfo.setSuccess();
                log.info("[{}][SUCCESS UPDATE BOOK]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(updateBooksRq.getBookId() + " is not exist");
                log.info("[{}][FAILED UPDATE BOOK]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

//    public ResponseInfo<Object> deleteBook(String bookId) {
//        ResponseInfo<Object> responseInfo = new ResponseInfo<>();
//
//        try {
//            bookRepository.deleteBook(bookId);
//            responseInfo.setSuccess();
//            log.info("[{}][SUCCESS DELETE BOOK][{}]", getClass().getSimpleName(), bookId);
//        } catch (Exception ex) {
//            log.info("[{}][FAILED DELETE BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }

//    public ResponseInfo<List<Book>> getAllBooks() {
//        ResponseInfo<List<Book>> responseInfo = new ResponseInfo<>();
//
//        try {
//            List<Book> books;
//            books = bookRepository.getAllBooks();
//            books.addAll(bookRepository.getAllBooks());
//            responseInfo.setSuccess(books);
//            log.info("[{}][SUCCESS GET ALL BOOK][DATA SIZE: {}]", getClass().getSimpleName(), books.size());
//        } catch (Exception ex) {
//            log.info("[{}][FAILED GET ALL BOOK][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
//            responseInfo.handleException(ex);
//        }
//        return responseInfo;
//    }

}
