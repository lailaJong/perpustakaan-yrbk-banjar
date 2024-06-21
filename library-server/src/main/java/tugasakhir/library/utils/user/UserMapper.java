package tugasakhir.library.utils.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.User;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.user.UpdateUserRq;
import tugasakhir.library.model.request.user.UserRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toUser(UserRq userRq);

    @Mapping(target = "userId", ignore = true)
    void updateUserFromUpdateUserRq(UpdateUserRq updateUserRq, @MappingTarget User user);
}