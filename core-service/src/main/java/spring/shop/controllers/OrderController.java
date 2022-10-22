package spring.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.shop.converters.OrderConverter;
import spring.shop.dto.OrderDetailsDto;
import spring.shop.dto.OrderDto;
import spring.shop.exceptions.ResourceNotFoundException;
import spring.shop.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping("/{cartName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto,
                            @PathVariable String cartName) {
        orderService.createOrder(username, orderDetailsDto, cartName);
    }

    @GetMapping()
    public List<OrderDto> getCurrentOrders(@RequestHeader String username) {
        return orderService.findOrderByUsername(username).stream().map(orderConverter::entityToDto)
                .collect(Collectors.toList());
    }
}
