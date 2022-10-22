package spring.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.shop.dto.Cart;
import spring.shop.dto.OrderDetailsDto;
import spring.shop.entities.Order;
import spring.shop.entities.OrderItem;
import spring.shop.entities.User;
import spring.shop.exceptions.ResourceNotFoundException;
import spring.shop.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductsService productsService;

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public void createOrder(User user, OrderDetailsDto orderDetailsDto, String cartName) {
        Cart currentCart = cartService.getCurrentCart(cartName);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems().stream().map(o -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            orderItem.setPricePerProduct(o.getPricePerProduct());
            orderItem.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found,")));
            return orderItem;
        }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        currentCart.clear();

    }

    public List<Order> findOrderByUsername(String username) {
        return orderRepository.findByUsername(username);
    }
}
