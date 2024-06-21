package tugasakhir.library.utils.book;

import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.request.book.BookRq;
import tugasakhir.library.model.request.book.UpdateBookRq;
import tugasakhir.library.utils.book.BooksMapper;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class BooksMapperImpl implements BooksMapper {

    @Override
    public Book toBook(BookRq bookRq) {
        if (bookRq == null) {
            return null;
        }
        Book book = new Book();
        book.setBookId(bookRq.getBookId());
        book.setBookTitle(bookRq.getBookTitle());
        book.setCategoryId(bookRq.getCategoryId());
        book.setPublisherId(bookRq.getPublisherId());
        book.setAuthorId(bookRq.getAuthorId());
        book.setLanguage(bookRq.getLanguage());
        book.setIsbn(bookRq.getIsbn());
        book.setNumberOfPages(bookRq.getNumberOfPages());
        book.setPublicationYear(bookRq.getPublicationYear());
        book.setSynopsis(bookRq.getSynopsis());
        book.setStatus(bookRq.getStatus());
        return book;
    }

    @Override
    public void updateBookFromUpdateBookRq(UpdateBookRq updateBookRq, Book book) {
        if ( updateBookRq == null ) {
            return;
        }

        if ( updateBookRq.getBookTitle() != null ) {
            book.setBookTitle( updateBookRq.getBookTitle() );
        }
        if ( updateBookRq.getCategoryId() != null ) {
            book.setCategoryId( updateBookRq.getCategoryId() );
        }
        if ( updateBookRq.getPublisherId() != null ) {
            book.setPublisherId( updateBookRq.getPublisherId() );
        }
        if ( updateBookRq.getAuthorId() != null ) {
            book.setAuthorId( updateBookRq.getAuthorId() );
        }
        if ( updateBookRq.getLanguage() != null ) {
            book.setLanguage( updateBookRq.getLanguage() );
        }
        if ( updateBookRq.getIsbn() != null ) {
            book.setIsbn( updateBookRq.getIsbn() );
        }
        if ( updateBookRq.getNumberOfPages() != 0 ) {
            book.setNumberOfPages( updateBookRq.getNumberOfPages() );
        }
        if ( updateBookRq.getPublicationYear() != null ) {
            book.setPublicationYear( updateBookRq.getPublicationYear() );
        }
        if ( updateBookRq.getSynopsis() != null ) {
            book.setSynopsis( updateBookRq.getSynopsis() );
        }
        if ( updateBookRq.getStatus() != null ) {
            book.setStatus( updateBookRq.getStatus() );
        }
    }
}