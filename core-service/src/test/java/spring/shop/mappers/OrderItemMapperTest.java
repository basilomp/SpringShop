package spring.shop.mappers;

import org.junit.jupiter.api.Test;
import spring.shop.dto.OrderItemDto;
import spring.shop.entities.Order;
import spring.shop.entities.OrderItem;
import spring.shop.entities.Product;


import static org.junit.jupiter.api.Assertions.*;

class OrderItemMapperTest {


    Product product = Product.builder()
            .id(1L)
            .title("Chocobar")
            .price(50)
            .build();

    Order order = Order.builder()
            .id(1L)
            .build();

    int quantity = 2;

    @Test
    void toEntity() {
        OrderItemDto dto = OrderItemDto.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .quantity(quantity)
                .pricePerProduct(product.getPrice())
                .price(product.getPrice() * quantity)
                .build();

        OrderItem item = OrderItemMapper.INSTANCE.toEntity(dto);

        assertEquals(dto.getProductId(), item.getProduct().getId());
        assertEquals(dto.getTitle(), item.getProduct().getTitle());
        assertEquals(dto.getQuantity(), item.getQuantity());
        assertEquals(dto.getPricePerProduct(), item.getPricePerProduct());
        assertEquals(dto.getPrice(), item.getPrice());
    }

    @Test
    void toDto() {
        OrderItem item = OrderItem.builder()
                .id(2L)
                .product(product)
                .order(order)
                .quantity(quantity)
                .pricePerProduct(product.getPrice())
                .price(product.getPrice() * quantity)
                .build();

        OrderItemDto dto = OrderItemMapper.INSTANCE.toDto(item);

        assertEquals(dto.getProductId(), item.getProduct().getId());
        assertEquals(dto.getTitle(), item.getProduct().getTitle());
        assertEquals(dto.getQuantity(), item.getQuantity());
        assertEquals(dto.getPricePerProduct(), item.getPricePerProduct());
        assertEquals(dto.getPrice(), item.getPrice());

    }
}