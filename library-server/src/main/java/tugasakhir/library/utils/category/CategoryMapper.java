package tugasakhir.library.utils.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.Category;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.category.CategoryRq;
import tugasakhir.library.model.request.category.UpdateCategoryRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category toCategory(CategoryRq categoryRq);

    @Mapping(target = "categoryId", ignore = true)
    void updateCategoryFromUpdateCategoryRq(UpdateCategoryRq updateCategoryRq, @MappingTarget Category category);
}