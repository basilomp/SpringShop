package spring.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.shop.dto.OrderDto;
import spring.shop.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);
}
