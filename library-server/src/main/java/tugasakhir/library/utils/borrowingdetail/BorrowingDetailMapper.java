package tugasakhir.library.utils.borrowingdetail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.BorrowingDetail;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.borrowingdetail.BorrowingDetailRq;
import tugasakhir.library.model.request.borrowingdetail.UpdateBorrowingDetailRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BorrowingDetailMapper {
    BorrowingDetailMapper INSTANCE = Mappers.getMapper(BorrowingDetailMapper.class);
    BorrowingDetail toBorrowingDetail(BorrowingDetailRq borrowingDetailRq);

    @Mapping(target = "borrowingId", ignore = true)
    void updateBorrowingDetailFromUpdateBorrowingDetailRq(UpdateBorrowingDetailRq updateBorrowingDetailRq, @MappingTarget BorrowingDetail borrowingDetail);
}