package tugasakhir.library.utils.role;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Role;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.role.RoleRq;
import tugasakhir.library.model.request.role.UpdateRoleRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    Role toRole(RoleRq roleRq);

    @Mapping(target = "roleId", ignore = true)
    void updateRoleFromUpdateRoleRq(UpdateRoleRq updateRoleRq, @MappingTarget Role role);
}