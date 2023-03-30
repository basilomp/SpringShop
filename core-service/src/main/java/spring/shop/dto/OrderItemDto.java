package spring.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.shop.entities.Product;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    @Schema(description = "Id of a product")
    private Long productId;

    @Schema(description = "Title of a product")
    private String title;

    @Schema(description = "Quantity of a product in an order")
    private int quantity;

    @Schema(description = "Price of a product in an order")
    private int pricePerProduct;

    @Schema(description = "Total price of a product in an order")
    private int price;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice() * this.quantity;
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }

}
