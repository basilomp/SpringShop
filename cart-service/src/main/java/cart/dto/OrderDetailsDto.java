package cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {

    @Schema(description = "Order recipient's address")
    private String address;

    @Schema(description = "Recipient's phone number")
    private String phone;
}
