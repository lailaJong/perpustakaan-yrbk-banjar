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
public class CategoryMapperImpl{

    public static Category toCategory(CategoryRq categoryRq, String id) {
        if (categoryRq == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(id);
        category.setCategoryName(categoryRq.getCategoryName());
        return category;
    }

    public static void updateCategoryFromUpdateCategoryRq(UpdateCategoryRq updateCategoryRq, Category category) {
        if ( updateCategoryRq == null ) {
            return;
        }

        if ( updateCategoryRq.getCategoryName() != null ) {
            category.setCategoryName( updateCategoryRq.getCategoryName() );
        }
    }
}