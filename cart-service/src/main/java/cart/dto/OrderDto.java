package cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    @Schema(description = "Order id")
    private Long id;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "List of items in an order")
    private List<OrderItemDto> itemDtoList;

    @Schema(description = "Total price oƒ items in an order")
    private Integer totalPrice;

    @Schema(description = "Address an order is to be sent to")
    private String address;

    @Schema(description = "Recipient's phone number")
    private String phone;

}
