package spring.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import spring.shop.dto.OrderItemDto;
import spring.shop.entities.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "product.title", source = "title")
    @Mapping(target = "product.price", source = "pricePerProduct")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "quantity", source = "quantity")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "title", source = "product.title")
    @Mapping(target = "pricePerProduct", source = "product.price")
    @Mapping(target = "price", source = "price")
    OrderItemDto toDto(OrderItem orderItem);
}
