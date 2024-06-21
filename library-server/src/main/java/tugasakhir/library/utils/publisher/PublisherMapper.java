package tugasakhir.library.utils.publisher;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Publisher;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.publisher.PublisherRq;
import tugasakhir.library.model.request.publisher.UpdatePublisherRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);
    Publisher toPublisher(PublisherRq publisherRq);

    @Mapping(target = "publisherId", ignore = true)
    void updatePublisherFromUpdatePublisherRq(UpdatePublisherRq updatePublisherRq, @MappingTarget Publisher publisher);
}