package tugasakhir.library.utils.bookshelf;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.BookShelf;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.bookshelf.BookShelfRq;
import tugasakhir.library.model.request.bookshelf.UpdateBookShelfRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookShelfMapper {
    BookShelfMapper INSTANCE = Mappers.getMapper(BookShelfMapper.class);
    BookShelf toBookShelf(BookShelfRq bookShelfRq);

    @Mapping(target = "bookShelfId", ignore = true)
    void updateBookShelfFromUpdateBookShelfRq(UpdateBookShelfRq updateBookShelfRq, @MappingTarget BookShelf bookShelf);
}