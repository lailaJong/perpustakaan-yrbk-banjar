package tugasakhir.library.utils.bookplacement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.BookPlacement;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.bookplacement.BookPlacementRq;
import tugasakhir.library.model.request.bookplacement.UpdateBookPlacementRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookPlacementsMapper {
    BookPlacementsMapper INSTANCE = Mappers.getMapper(BookPlacementsMapper.class);
    BookPlacement toBookPlacement(BookPlacementRq bookPlacementRq);

    @Mapping(target = "bookPlacementId", ignore = true)
    void updateBookPlacementFromUpdateBookPlacementRq(UpdateBookPlacementRq updateBookPlacementRq, @MappingTarget BookPlacement bookPlacement);
}