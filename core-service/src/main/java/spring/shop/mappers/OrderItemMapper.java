package spring.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.shop.dto.OrderItemDto;
import spring.shop.entities.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItem toEntity(OrderItemDto orderItemDto);

    OrderItemDto toDto(OrderItem orderItem);
}
