package spring.shop.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.shop.dto.OrderDto;
import spring.shop.dto.OrderItemDto;
import spring.shop.entities.Order;
import spring.shop.entities.OrderItem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;

    OrderItemDto itemDto1 = OrderItemDto.builder()
            .productId(10L)
            .title("Crisps")
            .pricePerProduct(101)
            .quantity(3)
            .build();

    OrderItemDto itemDto2 = OrderItemDto.builder()
            .productId(20L)
            .title("Corn flakes")
            .pricePerProduct(150)
            .quantity(2)
            .build();

    OrderItem item1 = OrderItemMapper.INSTANCE.toEntity(itemDto1);
    OrderItem item2 = OrderItemMapper.INSTANCE.toEntity(itemDto2);
    List<OrderItemDto> itemsDto = List.of(itemDto1, itemDto2);
    List<OrderItem> items = List.of(item1, item2);


    @Test
    void toEntity() {
        OrderDto orderDto = OrderDto.builder()
                .id(2L)
                .username("Test user")
                .itemDtoList(itemsDto)
                .address("Test Address Str.")
                .phone("88005553535")
                .build();

        Order order = OrderMapper.INSTANCE.toEntity(orderDto);

        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getUsername(), orderDto.getUsername());
        assertEquals(order.getItems().size(), orderDto.getItemDtoList().size());
        assertEquals(order.getItems().get(0).getProduct().getId(), orderDto.getItemDtoList().get(0).getProductId());
        assertEquals(order.getItems().get(0).getProduct().getPrice(), orderDto.getItemDtoList().get(0).getPricePerProduct());
        assertEquals(order.getItems().get(0).getProduct().getTitle(), orderDto.getItemDtoList().get(0).getTitle());
        assertEquals(order.getItems().get(0).getPrice(), orderDto.getItemDtoList().get(0).getPrice());
        assertEquals(order.getAddress(), orderDto.getAddress());
        assertEquals(order.getPhone(), orderDto.getPhone());
    }

    @Test
    void toDto() {
        Order order = Order.builder()
                .id(2L)
                .username("Test user")
                .items(items)
                .address("Test address Str")
                .phone("88005553535")
                .build();

        OrderDto dto = OrderMapper.INSTANCE.toDto(order);

        assertEquals(order.getId(), dto.getId());
        assertEquals(order.getUsername(), dto.getUsername());
        assertEquals(order.getItems().size(), dto.getItemDtoList().size());
        assertEquals(order.getItems().get(0).getProduct().getId(), dto.getItemDtoList().get(0).getProductId());
        assertEquals(order.getItems().get(0).getProduct().getPrice(), dto.getItemDtoList().get(0).getPricePerProduct());
        assertEquals(order.getItems().get(0).getProduct().getTitle(), dto.getItemDtoList().get(0).getTitle());
        assertEquals(order.getItems().get(0).getPrice(), dto.getItemDtoList().get(0).getPrice());
        assertEquals(order.getAddress(), dto.getAddress());
        assertEquals(order.getPhone(), dto.getPhone());

    }
}