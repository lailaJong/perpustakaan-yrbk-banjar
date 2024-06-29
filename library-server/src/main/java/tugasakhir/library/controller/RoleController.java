//package tugasakhir.library.controller;
//
//import jakarta.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tugasakhir.library.model.entity.Role;
//import tugasakhir.library.model.request.role.RoleRq;
//import tugasakhir.library.model.request.role.UpdateRoleRq;
//import tugasakhir.library.model.response.ResponseInfo;
//import tugasakhir.library.usecase.RoleUsecase;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/roles")
//@Slf4j
//public class RoleController {
//
//    @Autowired
//    private RoleUsecase roleUsecase;
//
//    @GetMapping("/all")
//    ResponseEntity<Object> getAllRoles(@RequestHeader(value = "request-id", required = false) String requestId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<List<Role>> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ALL ROLES][{}]", requestId);
//        responseInfo = roleUsecase.getAllRoles();
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @GetMapping("/id")
//    ResponseEntity<Object> getRoleById(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "roleId") String roleId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        ResponseInfo<Role> responseInfo;
//        log.info("[REQUEST RECEIVED - GET ROLE BY ID][{}][{}]", roleId, requestId);
//        responseInfo = roleUsecase.getRoleById(roleId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PostMapping("/create")
//    ResponseEntity<Object> createRole(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid RoleRq roleRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - ADD NEW ROLE][{}][PAYLOAD: {}]", requestId, roleRq);
//        ResponseInfo<Role> responseInfo = roleUsecase.addNewRole(roleRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @PutMapping("/update")
//    ResponseEntity<Object> updateRole(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestBody @Valid UpdateRoleRq updateRoleRq) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - UPDATE ROLE][{}][PAYLOAD: {}]", requestId, updateRoleRq);
//        ResponseInfo<Object> responseInfo = roleUsecase.updateRole(updateRoleRq);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//    @DeleteMapping("/delete")
//    ResponseEntity<Object> deleteRole(@RequestHeader(value = "request-id", required = false) String requestId,
//                                       @RequestParam(value = "roleId") String roleId) {
//        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
//        log.info("[REQUEST RECEIVED - DELETE ROLE][{}][ROLE ID: {}]", requestId, roleId);
//        ResponseInfo<Object> responseInfo = roleUsecase.deleteRole(roleId);
//        return ResponseEntity.status(responseInfo.getHttpStatusCode())
//                .headers(responseInfo.getHttpHeaders())
//                .body(responseInfo.getBody());
//    }
//
//}
