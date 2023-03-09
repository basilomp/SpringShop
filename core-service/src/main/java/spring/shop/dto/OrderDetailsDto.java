package spring.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {

    @Schema(description = "Recipient's address")
    private String address;

    @Schema(description = "Recipient's phone number")
    private String phone;
}
