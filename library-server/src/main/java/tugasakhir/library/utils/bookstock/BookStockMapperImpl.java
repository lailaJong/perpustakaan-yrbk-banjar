package tugasakhir.library.utils.bookstock;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.BookStock;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.bookstock.BookStockRq;
import tugasakhir.library.model.request.bookstock.UpdateBookStockRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class BookStockMapperImpl {

    public static BookStock toBookStock(BookStockRq bookStockRq, String id) {
        if (bookStockRq == null) {
            return null;
        }
        BookStock bookStock = new BookStock();
        bookStock.setBookStockId(id);
        bookStock.setBookId(bookStockRq.getBookId());
        bookStock.setStock(bookStockRq.getStock());
        return bookStock;
    }

    public static void updateBookStockFromUpdateBookStockRq(UpdateBookStockRq updateBookStockRq, BookStock bookStock) {
        if ( updateBookStockRq == null ) {
            return;
        }

        if ( updateBookStockRq.getBookId() != null ) {
            bookStock.setBookId( updateBookStockRq.getBookId() );
        }
        if ( updateBookStockRq.getStock() != -1 ) {
            bookStock.setStock( updateBookStockRq.getStock() );
        }
    }
}