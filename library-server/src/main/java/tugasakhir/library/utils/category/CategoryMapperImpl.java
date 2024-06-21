package tugasakhir.library.utils.category;

import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Category;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.category.CategoryRq;
import tugasakhir.library.model.request.category.UpdateCategoryRq;

/**
 * @author Putri Mele
 * on 18/06/2024
 */
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryRq categoryRq) {
        if (categoryRq == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(categoryRq.getCategoryId());
        category.setCategoryName(categoryRq.getCategoryName());
        return category;
    }

    @Override
    public void updateCategoryFromUpdateCategoryRq(UpdateCategoryRq updateCategoryRq, Category category) {
        if ( updateCategoryRq == null ) {
            return;
        }

        if ( updateCategoryRq.getCategoryName() != null ) {
            category.setCategoryName( updateCategoryRq.getCategoryName() );
        }
    }
}