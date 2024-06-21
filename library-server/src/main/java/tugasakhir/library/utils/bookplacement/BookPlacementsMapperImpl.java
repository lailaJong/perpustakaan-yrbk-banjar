package tugasakhir.library.utils.bookplacement;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.entity.BookPlacement;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.bookplacement.BookPlacementRq;
import tugasakhir.library.model.request.bookplacement.UpdateBookPlacementRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class BookPlacementsMapperImpl implements BookPlacementsMapper {

    @Override
    public BookPlacement toBookPlacement(BookPlacementRq bookPlacementRq) {
        if (bookPlacementRq == null) {
            return null;
        }
        BookPlacement bookPlacement = new BookPlacement();
        bookPlacement.setBookPlacementId(bookPlacementRq.getBookPlacementId());
        bookPlacement.setBookShelfId(bookPlacementRq.getBookShelfId());
        bookPlacement.setBookId(bookPlacementRq.getBookId());
        return bookPlacement;
    }

    @Override
    public void updateBookPlacementFromUpdateBookPlacementRq(UpdateBookPlacementRq updateBookPlacementRq, BookPlacement bookPlacement) {
        if ( updateBookPlacementRq == null ) {
            return;
        }

        if ( updateBookPlacementRq.getBookShelfId() != null ) {
            bookPlacement.setBookShelfId( updateBookPlacementRq.getBookShelfId() );
        }
        if ( updateBookPlacementRq.getBookId() != null ) {
            bookPlacement.setBookId( updateBookPlacementRq.getBookId() );
        }
    }
}