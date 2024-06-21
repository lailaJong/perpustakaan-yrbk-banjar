package tugasakhir.library.utils.orderdetail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tugasakhir.library.model.entity.Author;
import tugasakhir.library.model.entity.OrderDetail;
import tugasakhir.library.model.request.author.AuthorRq;
import tugasakhir.library.model.request.author.UpdateAuthorRq;
import tugasakhir.library.model.request.orderdetail.OrderDetailRq;
import tugasakhir.library.model.request.orderdetail.UpdateOrderDetailRq;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
    OrderDetail toOrderDetail(OrderDetailRq orderDetailRq);

    @Mapping(target = "orderId", ignore = true)
    void updateOrderDetailFromUpdateOrderDetailRq(UpdateOrderDetailRq updateOrderDetailRq, @MappingTarget OrderDetail orderDetail);
}