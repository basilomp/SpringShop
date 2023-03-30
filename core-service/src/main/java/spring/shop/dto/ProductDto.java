package spring.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Schema(description = "Id of a product")
    private Long id;

    @Schema(description = "Title of a product")
    private String title;

    @Schema(description = "Price of a product")
    private Integer price;
}
