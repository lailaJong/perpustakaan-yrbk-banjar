package tugasakhir.library.utils.book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Book;
import tugasakhir.library.model.request.book.BookRq;
import tugasakhir.library.model.request.book.UpdateBookRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BooksMapper {
    BooksMapper INSTANCE = Mappers.getMapper(BooksMapper.class);
    Book toBook(BookRq bookRq);

    @Mapping(target = "bookId", ignore = true)
    void updateBookFromUpdateBookRq(UpdateBookRq updateBookRq, @MappingTarget Book book);
}