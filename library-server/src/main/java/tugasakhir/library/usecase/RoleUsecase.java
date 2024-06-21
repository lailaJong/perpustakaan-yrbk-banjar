package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Role;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.role.RoleRq;
import tugasakhir.library.model.request.role.UpdateRoleRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.RoleRepository;
import tugasakhir.library.utils.role.RoleMapper;

import java.util.List;

@Component
@Slf4j
public class RoleUsecase {
    @Autowired
    private RoleRepository roleRepository;

    public ResponseInfo<List<Role>> getAllRoles() {
        ResponseInfo<List<Role>> responseInfo = new ResponseInfo<>();

        try {
            List<Role> roles;
            roles = roleRepository.getAllRoles();
            roles.addAll(roleRepository.getAllRoles());
            responseInfo.setSuccess(roles);
            log.info("[{}][SUCCESS GET ALL ROLE][DATA SIZE: {}]", getClass().getSimpleName(), roles.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL ROLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Role> getRoleById(String roleId) {
        ResponseInfo<Role> responseInfo = new ResponseInfo<>();

        try {
            Role role;
            role = roleRepository.getRoleById(roleId);
            responseInfo.setSuccess(role);
            log.info("[{}][SUCCESS GET ROLE][ID: {}]", getClass().getSimpleName(), roleId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ROLE][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), roleId, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Role> addNewRole(RoleRq roleRq) {
        ResponseInfo<Role> responseInfo = new ResponseInfo<>();

        try {
            Role role;
            roleRq.setRoleId(roleRepository.generateRoleId());
            role = RoleMapper.INSTANCE.toRole(roleRq);
            roleRepository.addRole(role);
            responseInfo.setSuccess(role);
            log.info("[{}][SUCCESS ADD NEW ROLE]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW ROLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateRole(UpdateRoleRq updateRoleRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Role role = roleRepository.getRoleById(updateRoleRq.getRoleId());
            if (role != null) {
                RoleMapper.INSTANCE.updateRoleFromUpdateRoleRq(updateRoleRq, role);
                roleRepository.updateRole(role);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE ROLE]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE ROLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteRole(String roleId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            roleRepository.deleteRole(roleId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE ROLE][{}]", getClass().getSimpleName(), roleId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE ROLE][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
