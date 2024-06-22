package tugasakhir.library.utils.user;

import tugasakhir.library.model.entity.User;
import tugasakhir.library.model.request.user.UpdateUserRq;
import tugasakhir.library.model.request.user.UserRq;
import tugasakhir.library.model.request.usermember.UpdateUserMemberRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class UserMapperImpl{

    public static User toUser(UserRq userRq) {
        if (userRq == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userRq.getUserId());
        user.setRoleId(userRq.getRoleId());
        user.setUsername(userRq.getUsername());
        user.setPassword(userRq.getPassword());
        return user;
    }

    public static void updateUserFromUpdateUserRq(UpdateUserRq updateUserRq, User user) {
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
    }

    public static void updateUserFromUpdateUserRq(UpdateUserMemberRq updateUserMemberRq, User user) {
        if ( updateUserMemberRq == null ) {
            return;
        }

        if ( updateUserMemberRq.getUsername() != null ) {
            user.setUsername( updateUserMemberRq.getUsername() );
        }
    }
}