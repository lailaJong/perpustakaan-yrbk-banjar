package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.User;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.user.UpdateUserRq;
import tugasakhir.library.model.request.user.UserRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.RoleRepository;
import tugasakhir.library.repository.UserRepository;
import tugasakhir.library.utils.user.UserMapperImpl;

import java.util.List;

@Component
@Slf4j
public class UserUsecase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public ResponseInfo<List<User>> getAllUsers() {
        ResponseInfo<List<User>> responseInfo = new ResponseInfo<>();

        try {
            List<User> users;
            users = userRepository.getAllUsers();
            users.addAll(userRepository.getAllUsers());
            responseInfo.setSuccess(users);
            log.info("[{}][SUCCESS GET ALL USER][DATA SIZE: {}]", getClass().getSimpleName(), users.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL USER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<User> getUserById(String userId) {
        ResponseInfo<User> responseInfo = new ResponseInfo<>();

        try {
            User user;
            user = userRepository.getUserById(userId);
            responseInfo.setSuccess(user);
            log.info("[{}][SUCCESS GET USER][ID: {}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET USER][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<User> getUserByUsername(String userName) {
        ResponseInfo<User> responseInfo = new ResponseInfo<>();

        try {
            User user;
            user = userRepository.getUserByUsername(userName);
            responseInfo.setSuccess(user);
            log.info("[{}][SUCCESS GET USER][USERNAME: {}]", getClass().getSimpleName(), userName);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET USER][USERNAME: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), userName, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<User> addNewUser(UserRq userRq) {
        ResponseInfo<User> responseInfo = new ResponseInfo<>();

        try {
            User user;
            userRq.setUserId(userRepository.generateUserId());
            userRq.setRoleId(roleRepository.getRoleByName("Member").getRoleId());
            user = UserMapperImpl.toUser(userRq);
            userRepository.addUser(user);
            responseInfo.setSuccess(user);
            log.info("[{}][SUCCESS ADD NEW USER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW USER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateUser(UpdateUserRq updateUserRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            User user = userRepository.getUserById(updateUserRq.getUserId());
            if (user != null) {
                UserMapperImpl.updateUserFromUpdateUserRq(updateUserRq, user);
                userRepository.updateUser(user);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE USER]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE USER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteUser(String userId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            userRepository.deleteUser(userId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE USER][{}]", getClass().getSimpleName(), userId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE USER][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

}
