//package tugasakhir.library.controller;
//
//import jakarta.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tugasakhir.library.model.entity.User;
//import tugasakhir.library.model.request.user.UpdateUserRq;
//import tugasakhir.library.model.request.user.UserRq;
//import tugasakhir.library.model.response.ResponseInfo;
//import tugasakhir.library.usecase.UserUsecase;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/users")
//@Slf4j
//public class UserController {
//
//    @Autowired
//    private UserUsecase userUsecase;
//
//    @GetMapping("/all")
//    ResponseEntity<Object> getAllUsers(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<User>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ALL USERS][{}]", requestId);
//        responseInfo = userUsecase.getAllUsers();
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    //get user by user id after login
//    @GetMapping("/id")
//    ResponseEntity<Object> getUserById(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "userId") String userId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<User> responseInfo;
//        log.info("[REQUEST RECEIVED - GET USER BY ID][{}][{}]", userId, requestId);
//        responseInfo = userUsecase.getUserById(userId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @GetMapping("/username")
//    ResponseEntity<Object> getUserByUsername(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "userName") String userName) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<User> responseInfo;
//        log.info("[REQUEST RECEIVED - GET USER BY USERNAME][{}][{}]", userName, requestId);
//        responseInfo = userUsecase.getUserByUsername(userName);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PostMapping("/create")
//    ResponseEntity<Object> createUser(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid UserRq userRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - ADD NEW USER][{}][PAYLOAD: {}]", requestId, userRq);
//        ResponseInfo<User> responseInfo = userUsecase.addNewUser(userRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PutMapping("/update")
//    ResponseEntity<Object> updateUser(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid UpdateUserRq updateUserRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - UPDATE USER][{}][PAYLOAD: {}]", requestId, updateUserRq);
//        ResponseInfo<Object> responseInfo = userUsecase.updateUser(updateUserRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @DeleteMapping("/delete")
//    ResponseEntity<Object> deleteUser(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "userId") String userId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - DELETE USER][{}][USER ID: {}]", requestId, userId);
//        ResponseInfo<Object> responseInfo = userUsecase.deleteUser(userId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//}
