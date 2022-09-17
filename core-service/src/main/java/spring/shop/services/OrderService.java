package spring.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.shop.dto.Cart;
import spring.shop.dto.OrderDetailsDto;
import spring.shop.entities.Order;
import spring.shop.entities.OrderItem;
import spring.shop.exceptions.ResourceNotFoundException;
import spring.shop.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductsService productsService;
    private final OrderRepository orderRepository;
    private final RestTemplate cartTemplate;

    public void createOrder(String username, OrderDetailsDto orderDetailsDto, String cartName) {
        Order order = new Order();
        Cart currentCart = cartTemplate.postForObject("http://localhost:8187/web-market-cart/api/v1/carts", cartName,
                Cart.class);
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
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
    public List<Order> findOrdersByUsername(String username) {
        try {
            return orderRepository.findByUsername(username);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Order> findOrderByUsername(String username) {
        return orderRepository.findByUsername(username);
    }
}
