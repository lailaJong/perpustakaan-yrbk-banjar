package tugasakhir.library.utils.author;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorsMapper {
    AuthorsMapper INSTANCE = Mappers.getMapper(AuthorsMapper.class);
    Author toAuthor(AuthorRq authorRq);

    @Mapping(target = "authorId", ignore = true)
    void updateAuthorFromUpdateAuthorRq(UpdateAuthorRq updateAuthorRq, @MappingTarget Author author);
}