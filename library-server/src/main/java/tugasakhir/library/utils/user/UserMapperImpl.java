package tugasakhir.library.utils.user;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.User;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.user.UpdateUserRq;
import tugasakhir.library.model.request.user.UserRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRq userRq) {
        if (userRq == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userRq.getUserId());
        user.setRoleId(userRq.getRoleId());
        user.setUsername(userRq.getUsername());
        user.setPassword(userRq.getPassword());
        user.setEmail(userRq.getEmail());
        return user;
    }

    @Override
    public void updateUserFromUpdateUserRq(UpdateUserRq updateUserRq, User user) {
        if ( updateUserRq == null ) {
            return;
        }

        if ( updateUserRq.getRoleId() != null ) {
            user.setRoleId( updateUserRq.getRoleId() );
        }
        if ( updateUserRq.getUsername() != null ) {
            user.setUsername( updateUserRq.getUsername() );
        }
        if ( updateUserRq.getPassword() != null ) {
            user.setPassword( updateUserRq.getPassword() );
        }
        if ( updateUserRq.getEmail() != null ) {
            user.setEmail( updateUserRq.getEmail() );
        }
    }
}