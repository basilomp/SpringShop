package spring.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.shop.converters.OrderConverter;
import spring.shop.dto.OrderDetailsDto;
import spring.shop.dto.OrderDto;
import spring.shop.entities.User;
import spring.shop.exceptions.ResourceNotFoundException;
import spring.shop.services.OrderService;
import spring.shop.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

//    @PostMapping
    @PostMapping("/{cartName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto,
                            @PathVariable String cartName) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(
                "User not found"));
        orderService.createOrder(user, orderDetailsDto, cartName);
    }

    @GetMapping()
    public List<OrderDto> getCurrentOrders(Principal principal) {
        return orderService.findOrderByUsername(principal.getName()).stream().map(orderConverter::entityToDto)
                .collect(Collectors.toList());
    }
}
