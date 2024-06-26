package tugasakhir.library.utils.book;

import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.request.book.BookRq;
import tugasakhir.library.model.request.book.UpdateBookRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
//public class BooksMapperImpl{
//
//    public static Book toBook(BookRq bookRq) {
//        if (bookRq == null) {
//            return null;
//        }
//        Book book = new Book();
//        book.setBookId(bookRq.getBookId());
//        book.setBookTitle(bookRq.getBookTitle());
//        book.setCategoryId(bookRq.getCategoryId());
//        book.setPublisherId(bookRq.getPublisherId());
//        book.setAuthorId(bookRq.getAuthorId());
//        book.setBookShelfId(bookRq.getBookShelfId());
//        book.setLanguage(bookRq.getLanguage());
//        book.setIsbn(bookRq.getIsbn());
//        book.setNumberOfPages(bookRq.getNumberOfPages());
//        book.setPublicationYear(bookRq.getPublicationYear());
//        book.setSynopsis(bookRq.getSynopsis());
//        return book;
//    }
//
//    public static void updateBookFromUpdateBookRq(UpdateBookRq updateBookRq, Book book) {
//        if ( updateBookRq == null ) {
//            return;
//        }
//
//        if ( updateBookRq.getBookTitle() != null ) {
//            book.setBookTitle( updateBookRq.getBookTitle() );
//        }
//        if ( updateBookRq.getCategoryId() != null ) {
//            book.setCategoryId( updateBookRq.getCategoryId() );
//        }
//        if ( updateBookRq.getPublisherId() != null ) {
//            book.setPublisherId( updateBookRq.getPublisherId() );
//        }
//        if ( updateBookRq.getAuthorId() != null ) {
//            book.setAuthorId( updateBookRq.getAuthorId() );
//        }
//        if ( updateBookRq.getBookShelfId() != null ) {
//            book.setBookShelfId( updateBookRq.getBookShelfId() );
//        }
//        if ( updateBookRq.getLanguage() != null ) {
//            book.setLanguage( updateBookRq.getLanguage() );
//        }
//        if ( updateBookRq.getIsbn() != null ) {
//            book.setIsbn( updateBookRq.getIsbn() );
//        }
//        if ( updateBookRq.getNumberOfPages() != 0 ) {
//            book.setNumberOfPages( updateBookRq.getNumberOfPages() );
//        }
//        if ( updateBookRq.getPublicationYear() != null ) {
//            book.setPublicationYear( updateBookRq.getPublicationYear() );
//        }
//        if ( updateBookRq.getSynopsis() != null ) {
//            book.setSynopsis( updateBookRq.getSynopsis() );
//        }
//    }
//}

public class BooksMapperImpl{

    public static Book toBook(BookRq bookRq, String categoryId, String publisherId, String authorId, String bookShelfId) {
        if (bookRq == null) {
            return null;
        }
        Book book = new Book();
        book.setBookId(bookRq.getBookId());
        book.setBookTitle(bookRq.getBookTitle());
        book.setCategoryId(categoryId);
        book.setPublisherId(publisherId);
        book.setAuthorId(authorId);
        book.setBookShelfId(bookShelfId);
        book.setLanguage(bookRq.getLanguage());
        book.setIsbn(bookRq.getIsbn());
        book.setNumberOfPages(bookRq.getNumberOfPages());
        book.setPublicationYear(bookRq.getPublicationYear());
        book.setSynopsis(bookRq.getSynopsis());
        book.setStock(bookRq.getStock());
        return book;
    }

    public static void updateBookFromUpdateBookRq(UpdateBookRq updateBookRq, Book book, String categoryId, String publisherId, String authorId, String bookShelfId) {
        if (updateBookRq == null) {
            return;
        }

        if (updateBookRq.getBookTitle() != null) {
            book.setBookTitle(updateBookRq.getBookTitle());
        }
        if (categoryId != null) {
            book.setCategoryId(categoryId);
        }
        if (publisherId != null) {
            book.setPublisherId(publisherId);
        }
        if (authorId != null) {
            book.setAuthorId(authorId);
        }
        if (bookShelfId != null) {
            book.setBookShelfId(bookShelfId);
        }
        if (updateBookRq.getLanguage() != null) {
            book.setLanguage(updateBookRq.getLanguage());
        }
        if (updateBookRq.getIsbn() != null) {
            book.setIsbn(updateBookRq.getIsbn());
        }
        if (updateBookRq.getNumberOfPages() != 0) {
            book.setNumberOfPages(updateBookRq.getNumberOfPages());
        }
        if (updateBookRq.getPublicationYear() != null) {
            book.setPublicationYear(updateBookRq.getPublicationYear());
        }
        if (updateBookRq.getSynopsis() != null) {
            book.setSynopsis(updateBookRq.getSynopsis());
        }
    }
}