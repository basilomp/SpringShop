package spring.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    @Schema(description = "Id of an order")
    private Long id;

    @Schema(description = "Username of a user")
    private String username;

    @Schema(description = "List of products in an order")
    private List<OrderItemDto> itemDtoList;

    @Schema(description = "Total price of items in an order")
    private Integer totalPrice;

    @Schema(description = "Recipient's address")
    private String address;

    @Schema(description = "Recipient's phone number")
    private String phone;
}

