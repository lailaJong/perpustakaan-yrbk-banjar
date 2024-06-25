package tugasakhir.library.utils.borrowingdetail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Borrowing;
import tugasakhir.library.model.request.borrowingdetail.BorrowingDetailRq;
import tugasakhir.library.model.request.borrowingdetail.UpdateBorrowingDetailRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BorrowingDetailMapper {
    BorrowingDetailMapper INSTANCE = Mappers.getMapper(BorrowingDetailMapper.class);
    Borrowing toBorrowingDetail(BorrowingDetailRq borrowingDetailRq);

    @Mapping(target = "borrowingId", ignore = true)
    void updateBorrowingDetailFromUpdateBorrowingDetailRq(UpdateBorrowingDetailRq updateBorrowingDetailRq, @MappingTarget Borrowing borrowingDetail);
}