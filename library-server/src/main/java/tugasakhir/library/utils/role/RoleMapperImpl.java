package tugasakhir.library.utils.role;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Role;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.role.RoleRq;
import tugasakhir.library.model.request.role.UpdateRoleRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toRole(RoleRq roleRq) {
        if (roleRq == null) {
            return null;
        }
        Role role = new Role();
        role.setRoleId(roleRq.getRoleId());
        role.setRoleName(roleRq.getRoleName());
        return role;
    }

    @Override
    public void updateRoleFromUpdateRoleRq(UpdateRoleRq updateRoleRq, Role role) {
        if ( updateRoleRq == null ) {
            return;
        }

        if ( updateRoleRq.getRoleName() != null ) {
            role.setRoleName( updateRoleRq.getRoleName() );
        }
    }
}