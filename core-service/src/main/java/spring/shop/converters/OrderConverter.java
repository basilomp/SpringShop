package spring.shop.converters;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import spring.shop.dto.OrderDto;
import spring.shop.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

//    public Order dtoToEntity(OrderDto orderDto) {
//        throw new UnsupportedOperationException();
//    }

//    public Order dtoToEntity(OrderDto orderDto) {
//        Order order = new Order();
//        order.setUser(order.getUser());
//        order.setTotalPrice(order.getTotalPrice());
//        order.setAddress(order.getAddress());
//        return order;
//    }

    public OrderDto entityToDto(Order order) {
        OrderDto out = new OrderDto();
        out.setId(order.getId());
        out.setAddress(order.getAddress());
        out.setPhone(order.getPhone());
        out.setTotalPrice(order.getTotalPrice());
        out.setUsername(order.getUsername());
        out.setItemDtoList(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        return out;
    }
}