package tugasakhir.library.utils.memberstatus;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.MemberStatus;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.memberstatus.MemberStatusRq;
import tugasakhir.library.model.request.memberstatus.UpdateMemberStatusRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberStatusMapper {
    MemberStatusMapper INSTANCE = Mappers.getMapper(MemberStatusMapper.class);
    MemberStatus toMemberStatus(MemberStatusRq memberStatusRq);

    @Mapping(target = "memberStatusId", ignore = true)
    void updateMemberStatusFromUpdateMemberStatusRq(UpdateMemberStatusRq updateMemberStatusRq, @MappingTarget MemberStatus memberStatus);
}