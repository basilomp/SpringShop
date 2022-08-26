package spring.shop.converters;

import org.springframework.stereotype.Component;
import spring.shop.dto.OrderItemDto;
import spring.shop.entities.OrderItem;

@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(),
                orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
