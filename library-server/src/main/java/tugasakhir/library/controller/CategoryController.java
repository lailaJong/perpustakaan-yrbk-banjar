package tugasakhir.library.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugasakhir.library.model.entity.Category;
import tugasakhir.library.model.request.category.CategoryRq;
import tugasakhir.library.model.request.category.UpdateCategoryRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.usecase.CategoryUsecase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryUsecase categoryUsecase;

    @GetMapping("/all")
    ResponseEntity<Object> getAllCategories(@RequestHeader(value = "request-id", required = false) String requestId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<Category>> responseInfo;
        log.info("[REQUEST RECEIVED - GET ALL CATEGORIES][{}]", requestId);
        responseInfo = categoryUsecase.getAllCategories();
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/id")
    ResponseEntity<Object> getCategoryById(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "categoryId") String categoryId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<Category> responseInfo;
        log.info("[REQUEST RECEIVED - GET CATEGORY BY ID][{}][{}]", categoryId, requestId);
        responseInfo = categoryUsecase.getCategoryById(categoryId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @GetMapping("/name")
    ResponseEntity<Object> getCategoryByName(@RequestHeader(value = "request-id", required = false) String requestId,
                                           @RequestParam(value = "categoryName") String categoryName) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        ResponseInfo<List<Category>> responseInfo;
        log.info("[REQUEST RECEIVED - GET CATEGORY BY NAME][{}][{}]", categoryName, requestId);
        responseInfo = categoryUsecase.getCategoryByName(categoryName);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PostMapping("/create")
    ResponseEntity<Object> createCategory(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid CategoryRq categoryRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - ADD NEW CATEGORY][{}][PAYLOAD: {}]", requestId, categoryRq);
        ResponseInfo<Category> responseInfo = categoryUsecase.addNewCategory(categoryRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @PutMapping("/update")
    ResponseEntity<Object> updateCategory(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestBody @Valid UpdateCategoryRq updateCategoryRq) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - UPDATE CATEGORY][{}][PAYLOAD: {}]", requestId, updateCategoryRq);
        ResponseInfo<Object> responseInfo = categoryUsecase.updateCategory(updateCategoryRq);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteCategory(@RequestHeader(value = "request-id", required = false) String requestId,
                                       @RequestParam(value = "categoryId") String categoryId) {
        if (requestId == null || requestId.isEmpty()) requestId = UUID.randomUUID().toString();
        log.info("[REQUEST RECEIVED - DELETE CATEGORY][{}][CATEGORY ID: {}]", requestId, categoryId);
        ResponseInfo<Object> responseInfo = categoryUsecase.deleteCategory(categoryId);
        return ResponseEntity.status(responseInfo.getHttpStatusCode())
                .headers(responseInfo.getHttpHeaders())
                .body(responseInfo.getBody());
    }

}
