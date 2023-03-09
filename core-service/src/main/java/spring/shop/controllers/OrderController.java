package spring.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.shop.converters.OrderConverter;
import spring.shop.dto.OrderDetailsDto;
import spring.shop.dto.OrderDto;
import spring.shop.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "OrderController", description = "Controller for handling order requests")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping("/{cartName}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Order creation request")
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto,
                            @PathVariable String cartName) {
        orderService.createOrder(username, orderDetailsDto, cartName);
    }

    @GetMapping
    @Operation(summary = "Request to show all current orders by username")
    public List<OrderDto> getCurrenUrders(@RequestHeader String username){
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
