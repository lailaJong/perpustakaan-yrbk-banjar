package tugasakhir.library.utils.officer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Officer;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.officer.OfficerRq;
import tugasakhir.library.model.request.officer.UpdateOfficerRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OfficerMapper {
    OfficerMapper INSTANCE = Mappers.getMapper(OfficerMapper.class);
    Officer toOfficer(OfficerRq officerRq);

    @Mapping(target = "officerId", ignore = true)
    void updateOfficerFromUpdateOfficerRq(UpdateOfficerRq updateOfficerRq, @MappingTarget Officer officer);
}