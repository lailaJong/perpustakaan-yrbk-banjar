package tugasakhir.library.utils.member;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Member;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.member.MemberRq;
import tugasakhir.library.model.request.member.UpdateMemberRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MembersMapper {
    MembersMapper INSTANCE = Mappers.getMapper(MembersMapper.class);
    Member toMember(MemberRq memberRq);

    @Mapping(target = "memberId", ignore = true)
    void updateMemberFromUpdateMemberRq(UpdateMemberRq updateMemberRq, @MappingTarget Member member);
}