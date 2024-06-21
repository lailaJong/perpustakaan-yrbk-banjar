package tugasakhir.library.utils.bookstock;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.BookStock;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.bookstock.BookStockRq;
import tugasakhir.library.model.request.bookstock.UpdateBookStockRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookStockMapper {
    BookStockMapper INSTANCE = Mappers.getMapper(BookStockMapper.class);
    BookStock toBookStock(BookStockRq bookStockRq);

    @Mapping(target = "bookStockId", ignore = true)
    void updateBookStockFromUpdateBookStockRq(UpdateBookStockRq updateBookStockRq, @MappingTarget BookStock bookStock);
}