package tugasakhir.library.utils.bookshelf;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.entity.BookShelf;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.bookshelf.BookShelfRq;
import tugasakhir.library.model.request.bookshelf.UpdateBookShelfRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class BookShelfMapperImpl{

    public static BookShelf toBookShelf(BookShelfRq bookShelfRq, String bookShelfId) {
        if (bookShelfRq == null) {
            return null;
        }
        BookShelf bookShelf = new BookShelf();
        bookShelf.setBookShelfId(bookShelfId);
        bookShelf.setBookShelfCode(bookShelfRq.getBookShelfCode());
        return bookShelf;
    }

    public static void updateBookShelfFromUpdateBookShelfRq(UpdateBookShelfRq updateBookShelfRq, BookShelf bookShelf) {
        if ( updateBookShelfRq == null ) {
            return;
        }

        if ( updateBookShelfRq.getBookShelfCode() != null ) {
            bookShelf.setBookShelfCode( updateBookShelfRq.getBookShelfCode() );
        }
    }
}