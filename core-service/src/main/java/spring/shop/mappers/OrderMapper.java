package spring.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import spring.shop.dto.OrderDto;
import spring.shop.dto.OrderItemDto;
import spring.shop.entities.Order;

import java.util.List;

@Mapper(uses = { OrderItemMapper.class },
        componentModel = "default")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "items", source = "itemDtoList")
    Order toEntity(OrderDto orderDto);

    @Mapping(target = "itemDtoList", source = "items")
    OrderDto toDto(Order order);
}
