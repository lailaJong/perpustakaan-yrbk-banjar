package tugasakhir.library.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tugasakhir.library.model.entity.Category;
import tugasakhir.library.model.exception.NotFoundException;
import tugasakhir.library.model.request.category.CategoryRq;
import tugasakhir.library.model.request.category.UpdateCategoryRq;
import tugasakhir.library.model.response.ResponseInfo;
import tugasakhir.library.repository.CategoryRepository;
import tugasakhir.library.utils.category.CategoryMapperImpl;

import java.util.List;

@Component
@Slf4j
public class CategoryUsecase {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseInfo<List<Category>> getAllCategories() {
        ResponseInfo<List<Category>> responseInfo = new ResponseInfo<>();

        try {
            List<Category> categories;
            categories = categoryRepository.getAllCategories();
            responseInfo.setSuccess(categories);
            log.info("[{}][SUCCESS GET ALL CATEGORIES][DATA SIZE: {}]", getClass().getSimpleName(), categories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL CATEGORIES][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<Category>> getCategoryByName(String categoryName) {
        ResponseInfo<List<Category>> responseInfo = new ResponseInfo<>();

        try {
            List <Category> category;
            category = categoryRepository.getAllCategoryByName(categoryName);
            responseInfo.setSuccess(category);
            log.info("[{}][SUCCESS GET CATEGORY][NAME: {}]", getClass().getSimpleName(), categoryName);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET CATEGORY][NAME: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), categoryName, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Category> getCategoryById(String categoryId) {
        ResponseInfo<Category> responseInfo = new ResponseInfo<>();

        try {
            Category category;
            category = categoryRepository.getCategoryById(categoryId);
            responseInfo.setSuccess(category);
            log.info("[{}][SUCCESS GET CATEGORY][ID: {}]", getClass().getSimpleName(), categoryId);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET CATEGORY][ID: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), categoryId, ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Category> addNewCategory(CategoryRq categoryRq) {
        ResponseInfo<Category> responseInfo = new ResponseInfo<>();

        try {
            Category category;
            boolean isExist = categoryRepository.existsByCategoryName(categoryRq.getCategoryName());
            if (!isExist) {
                String id = categoryRepository.generateCategoryId();
                category = CategoryMapperImpl.toCategory(categoryRq, id);
                categoryRepository.addCategory(category);
                responseInfo.setSuccess(category);
                log.info("[{}][SUCCESS ADD NEW CATEGORY]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(categoryRq.getCategoryName() + " is already exist");
                log.info("[{}][FAILED ADD NEW CATEGORY]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW CATEGORY][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateCategory(UpdateCategoryRq updateCategoryRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            boolean isExist = categoryRepository.existsByCategoryId(updateCategoryRq.getCategoryId());
            if (isExist) {
                Category category = categoryRepository.getCategoryById(updateCategoryRq.getCategoryId());
                CategoryMapperImpl.updateCategoryFromUpdateCategoryRq(updateCategoryRq, category);
                categoryRepository.updateCategory(category);

                responseInfo.setSuccess();
                log.info("[{}][SUCCESS UPDATE CATEGORY]", getClass().getSimpleName());
            } else {
                responseInfo.setBussinessError(updateCategoryRq.getCategoryId() + " is not exist");
                log.info("[{}][FAILED UPDATE CATEGORY]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE CATEGORY][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteCategory(String categoryId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            boolean isExist = categoryRepository.existsByCategoryId(categoryId);
            if (isExist) {
                categoryRepository.deleteCategory(categoryId);
                responseInfo.setSuccess();
                log.info("[{}][SUCCESS DELETE CATEGORY][{}]", getClass().getSimpleName(), categoryId);
            } else {
                responseInfo.setBussinessError(categoryId + " is not exist");
                log.info("[{}][FAILED DELETE CATEGORY]", getClass().getSimpleName());
            }
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE CATEGORY][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.handleException(ex);
        }
        return responseInfo;
    }

}
