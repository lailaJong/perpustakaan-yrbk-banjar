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
            categories.addAll(categoryRepository.getAllCategories());
            responseInfo.setSuccess(categories);
            log.info("[{}][SUCCESS GET ALL CATEGORIES][DATA SIZE: {}]", getClass().getSimpleName(), categories.size());
        } catch (Exception ex) {
            log.info("[{}][FAILED GET ALL CATEGORIES][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
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
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<List<Category>> getCategoryByName(String categoryName) {
        ResponseInfo<List<Category>> responseInfo = new ResponseInfo<>();

        try {
            List <Category> category;
            category = categoryRepository.getCategoryByName(categoryName);
            responseInfo.setSuccess(category);
            log.info("[{}][SUCCESS GET CATEGORY][NAME: {}]", getClass().getSimpleName(), categoryName);
        } catch (Exception ex) {
            log.info("[{}][FAILED GET CATEGORY][NAME: {}][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), categoryName, ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Category> addNewCategory(CategoryRq categoryRq) {
        ResponseInfo<Category> responseInfo = new ResponseInfo<>();

        try {
            Category category;
            categoryRq.setCategoryId(categoryRepository.generateCategoryId());
            category = CategoryMapperImpl.toCategory(categoryRq);
            categoryRepository.addCategory(category);
            responseInfo.setSuccess(category);
            log.info("[{}][SUCCESS ADD NEW CATEGORY]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED ADD NEW CATEGORY][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

    public ResponseInfo<Object> updateCategory(UpdateCategoryRq updateCategoryRq) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            Category category = categoryRepository.getCategoryById(updateCategoryRq.getCategoryId());
            if (category != null) {
                CategoryMapperImpl.updateCategoryFromUpdateCategoryRq(updateCategoryRq, category);
                categoryRepository.updateCategory(category);

                responseInfo.setSuccess();
            } else {
                throw new NotFoundException();
            }
            log.info("[{}][SUCCESS UPDATE CATEGORY]", getClass().getSimpleName());
        } catch (Exception ex) {
            log.info("[{}][FAILED UPDATE CATEGORY][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }


    public ResponseInfo<Object> deleteCategory(String categoryId) {
        ResponseInfo<Object> responseInfo = new ResponseInfo<>();

        try {
            categoryRepository.deleteCategory(categoryId);
            responseInfo.setSuccess();
            log.info("[{}][SUCCESS DELETE CATEGORY][{}]", getClass().getSimpleName(), categoryId);
        } catch (Exception ex) {
            log.info("[{}][FAILED DELETE CATEGORY][CAUSE: {}]", getClass().getSimpleName(), ex.getClass().getSimpleName(), ex);
            responseInfo.setCommonException(ex);
        }
        return responseInfo;
    }

}
